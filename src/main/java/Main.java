import manager.ServerManager;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Main {
    public static void main(String args[]) throws IOException, InterruptedException {
        ServerManager server = new ServerManager();
        InputStreamReader r = new InputStreamReader(System.in);
        BufferedReader br = new BufferedReader(r);
        System.out.println("Input your message to client (for Server Stream or Client Server Stream Call):");
        while (true) {
            String message = br.readLine();
            // With send empty string, it means stop the service
            if (message.equals("quit")) {
                break;
            }
            server.sendMessageToClient(message);
        }
        server.stopServer();

    }
}
