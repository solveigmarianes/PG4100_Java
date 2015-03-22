import sun.security.util.*;

import java.io.*;
import java.net.*;
import java.util.*;

/**
 * Created by solveigmarianes on 17.03.15.
 */
public class Client implements AutoCloseable {
    private Socket socket;
    private Listener listener;
    private Writer writer;

    public Client(final Socket socket) throws IOException {
        setSocket(socket);
        new Thread(listener = new Listener(socket.getInputStream())).start();
        new Thread(writer = new Writer(socket.getOutputStream())).start();
    }

    public static void main(String[] args) throws IOException {
        String serverAddress = "localhost";
        int serverPort = 20301;
        Client client = new Client(new Socket(serverAddress, serverPort));
    }

    public void setSocket(Socket socket) {
        this.socket = socket;
    }

    @Override
    public void close() throws IOException {
        listener.close();
        writer.close();
        socket.close();
        Debug.println("Client Socket", "Closed");
        System.exit(0);
    }

    public class Listener implements Runnable, Closeable {
        public DataInputStream in;

        Listener(InputStream inputStream) {
            in = new DataInputStream(inputStream);
        }

        @Override
        public void run() {
            String message;
            boolean run = true;
            try {
                while (((message = in.readUTF()) != null) && run) {
                    System.out.print(message);
                    if (message.equalsIgnoreCase("Quiz avsluttet")) {
                        String result = in.readUTF();
                        System.out.println(result + "\n\n");
                        run = false;
                    }
                }
            } catch (IOException e) {
                try {
                    Client.this.close();
                } catch (IOException ioe) {
                    e.printStackTrace();
                }
            }
        }

        @Override
        public void close() throws IOException {
            Debug.println("\nListener InputStream", "Closed");
            in.close();
        }
    }

    public class Writer implements Runnable, Closeable {
        private DataOutputStream out;

        public Writer(OutputStream outputStream) throws IOException {
            out = new DataOutputStream(outputStream);
            out.flush();
        }

        @Override
        public void run() {
            Scanner in = new Scanner(new InputStreamReader(System.in));
            String message;
            try {
                while ((message = in.nextLine()) != null) {
                    out.writeUTF(message);
                }
            } catch (IOException e) {
                try {
                    Client.this.close();
                } catch (IOException ioe) {
                    e.printStackTrace();
                }
            }
        }

        @Override
        public void close() throws IOException {
            Debug.println("Writer OutputStream", "Closed");
            out.close();
        }
    }
}
