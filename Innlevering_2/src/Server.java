import java.io.*;
import java.net.*;
import java.sql.*;
import java.util.*;
import java.util.concurrent.*;

/**
 * Created by solveigmarianes on 17.03.15.
 */
public class Server implements Runnable, AutoCloseable {
    private final ServerSocket serverSocket;
    private final ExecutorService executor;
    private final List<ClientConnection> clients = new ArrayList<>();
    private boolean connectionIsOpen = true;

    public Server() throws IOException {
        serverSocket = new ServerSocket(20301);
        executor = Executors.newCachedThreadPool();
    }

    public static void main(String[] args) {
        try (Server server = new Server()) {
            server.start();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void start() throws IOException, SQLException {
        System.out.println("Server started, waiting for ClientConnections ...");
        while (connectionIsOpen) {
            Socket accept = serverSocket.accept();
            ClientConnection client = new ClientConnection(accept);
            clients.add(client);
            executor.execute(client);
        }
    }

    @Override
    public void run() {
        try {
            start();
        } catch (Exception e) {
            connectionIsOpen = false;
            System.out.println("Serveren ble stengt ned");
            e.printStackTrace();
            try {
                this.close();
            } catch (Exception e1) {
                e1.printStackTrace();
            }
        }
    }

    @Override
    public void close() throws Exception {
        serverSocket.close();
        clients.forEach(clientConnection -> {
            try {
                clientConnection.close();
            } catch (Exception e) {
                e.printStackTrace();
            }
        });
    }
}
