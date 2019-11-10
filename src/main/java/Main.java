import manager.ServerManager;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Main {
    public static void main(String args[]) throws IOException, InterruptedException {
        ServerManager server = new ServerManager();
        InputStreamReader r = new InputStreamReader(System.in);
        BufferedReader br = new BufferedReader(r);
        System.out.println("Type your message for client");
        while (true) {
            String message = br.readLine();
            if (message.equals("")) {
                break;
            }
            server.sendMessageToClient(message);
        }
        server.stopServer();

    }
}
