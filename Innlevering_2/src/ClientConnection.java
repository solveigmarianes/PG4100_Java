import sun.security.util.*;

import java.io.*;
import java.net.*;
import java.sql.*;

/**
 * Created by solveigmarianes on 18.03.15.
 */
public class ClientConnection implements Runnable, AutoCloseable {
    private Quiz quiz;
    private DataInputStream in;
    private DataOutputStream out;
    private boolean continueQuiz = true;

    public ClientConnection(Socket accept) throws IOException, SQLException {
        quiz = new Quiz();
        System.out.println(accept.getInetAddress() + " koblet til");
        in = new DataInputStream(accept.getInputStream());
        out = new DataOutputStream(accept.getOutputStream());
    }

    @Override
    public void run() {
        try {
            out.writeUTF(explainRules());
            int numberOfQuestions = 0;
            out.writeUTF("Vil du delta i forfatter-quiz? j/n: ");
            String answer = in.readUTF();
            if ((answer.equalsIgnoreCase("j")) || (answer.equalsIgnoreCase("ja"))) {
                do {
                    numberOfQuestions++;
                    quiz.getNewBookFromList();
                    out.writeUTF(quiz.askQuestion());
                    answer = in.readUTF();
                    out.writeUTF(quiz.respondToAnswer(quiz.answersCorrectly(answer)) + "\n");

                    out.writeUTF("Vil du fortsette? j/n: ");
                    answer = in.readUTF();
                    if ((answer.equalsIgnoreCase("n")) || (answer.equalsIgnoreCase("nei"))) {
                        continueQuiz = false;
                        out.writeUTF("Quiz avsluttet\n");
                        out.writeUTF("Du fikk " + quiz.getScore() + " av " + numberOfQuestions + " riktige");
                        close();
                    }
                } while (continueQuiz);
            } else {
                out.writeUTF("Quiz avsluttet\n");
                close();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private String explainRules() {
        StringBuilder string = new StringBuilder();
        return string.append("----------- REGLER FOR FORFATTER-QUIZ -----------\n")
                .append("Du får oppgitt boktittel og utgivelsesår. Du skal gjette navnet til forfatteren.\n")
                .append("For å få svaret ditt godkjent, må: \n - Både fornavn og etternavn må være med, men rekkefølgen betyr ikke noe.\n")
                .append(" - Navnet må skrives slik det er allment kjent.\n")
                .append("\t\tEksempel: 'H. C. Andersen' godkjennes - 'Hans Christian Andersen' godkjennes ikke.\n")
                .append(" - Initialer må skrives med punktum og mellomrom.\n")
                .append(" - Både store og små bokstaver godkjennes.\n")
                .append("Du får poengsum når du avslutter quizen.\n\n").toString();
    }

    @Override
    public void close() throws Exception {
        Debug.println("ClientConnection Input/OutputStreams and Quiz", "Closed");
        in.close();
        out.close();
        quiz.close();
    }
}
