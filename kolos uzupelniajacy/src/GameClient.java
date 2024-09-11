import java.io.*;
import java.net.*;

public class GameClient {
    private Socket socket;
    private BufferedReader in;
    private PrintWriter out;

    public GameClient(String host, int port) throws IOException {
        socket = new Socket(host, port);
        in = new BufferedReader(new InputStreamReader(socket.getInputStream()));
        out = new PrintWriter(socket.getOutputStream(), true);
    }

    public void start() {
        try {
            BufferedReader consoleInput = new BufferedReader(new InputStreamReader(System.in));
            String serverMessage;

            while (true) {
                // Receive and display the current board state from the server
                while ((serverMessage = in.readLine()) != null) {
                    if (serverMessage.equals("END")) {
                        break;
                    }
                    System.out.println(serverMessage);
                }

                // Inform the user it's their turn
                System.out.println("Enter your move (row,col): ");
                String move = consoleInput.readLine();
                sendMove(move);

                // Optionally handle server responses or other messages
                String response = in.readLine();
                if (response != null) {
                    System.out.println("Server response: " + response);
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            closeConnections();
        }
    }

    public void sendMove(String move) throws IOException {
        out.println(move);
        out.flush();
    }

    public void closeConnections() {
        try {
            if (in != null) in.close();
            if (out != null) out.close();
            if (socket != null) socket.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) {
        if (args.length != 2) {
            System.out.println("Usage: java GameClient <host> <port>");
            return;
        }

        String host = args[0];
        int port = Integer.parseInt(args[1]);

        try {
            GameClient client = new GameClient(host, port);
            client.start();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
