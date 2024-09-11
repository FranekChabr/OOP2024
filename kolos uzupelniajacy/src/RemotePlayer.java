import java.io.*;
import java.net.*;

public class RemotePlayer extends Player {
    private final Socket socket;
    private BufferedReader in;
    private PrintWriter out;

    public RemotePlayer(Symbol symbol, String host, int port) throws IOException {
        super(symbol);
        // Nawiązanie połączenia z serwerem
        socket = new Socket(host, port);
        in = new BufferedReader(new InputStreamReader(socket.getInputStream()));
        out = new PrintWriter(socket.getOutputStream(), true);
    }

    @Override
    public String playTurn() {
        try {
            // Oczekiwanie na informację o kolejności ruchu
            System.out.println(inform());

            // Gracz wprowadza ruch (np. "1,2")
            BufferedReader consoleInput = new BufferedReader(new InputStreamReader(System.in));
            System.out.println("Enter your move (row,col): ");
            String move = consoleInput.readLine();

            // Wysłanie ruchu do serwera
            sendMove(move);

            // Oczekiwanie na potwierdzenie od serwera
            String response = receiveMessage();
            System.out.println("Server response: " + response);

            return move;
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null; // Na wypadek błędu
    }

    public void sendMove(String move) throws IOException {
        // Wysłanie ruchu do serwera
        out.println(move);
        out.flush();
    }

    public String receiveMessage() throws IOException {
        // Odbieranie wiadomości od serwera
        return in.readLine();
    }

    public void receiveBoardState() throws IOException {
        // Oczekiwanie na odbiór stanu planszy od serwera
        String boardState;
        while ((boardState = in.readLine()) != null) {
            System.out.println(boardState); // Wyświetlanie odbieranego stanu planszy
        }
    }

    public void closeConnections() throws IOException {
        // Zamknięcie połączenia sieciowego
        in.close();
        out.close();
        socket.close();
    }

    @Override
    public String inform() {
        return "Player " + getSymbol() + "'s turn via remote connection.";
    }
}
