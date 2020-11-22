package ConnectToServer;

import Constants.Constants;
import Models.Employee;



import java.io.*;
import java.net.Socket;
import java.util.LinkedList;

public class Client{

    public static InteractionsWithServer interactionsWithServer;
    public void connectToServer() {
        try {
            Socket clientSocket = new Socket(Constants.host, Constants.port);
            interactionsWithServer = new InteractionsWithServer(clientSocket);

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

}
