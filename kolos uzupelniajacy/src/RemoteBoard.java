import java.io.*;
import java.net.*;

public class RemoteBoard extends Board {
    private final ServerSocket serverSocket;
    private Socket clientSocket;
    private BufferedReader in;
    private PrintWriter out;

    public RemoteBoard(int port) throws IOException {
        // Inicjalizacja serwera nasłuchującego na określonym porcie
        serverSocket = new ServerSocket(port);
    }

    public void start() throws IOException {
        // Oczekiwanie na połączenie klienta
        System.out.println("Waiting for a client to connect...");
        clientSocket = serverSocket.accept();  // Akceptowanie połączenia
        System.out.println("Client connected.");

        // Inicjalizacja strumieni wejścia/wyjścia
        in = new BufferedReader(new InputStreamReader(clientSocket.getInputStream()));
        out = new PrintWriter(clientSocket.getOutputStream(), true);

        // Przykładowy sposób obsługi gry (zakładając, że playerX to serwer, playerO to klient)
        Player playerX = new HumanPlayer(Symbol.X); // Gracz serwerowy
        Player playerO = new RemotePlayer(Symbol.O, "localhost", serverSocket.getLocalPort()); // Gracz zdalny

        // Rozpoczęcie gry
        playGame(playerX, playerO);

        // Zamknięcie połączeń po zakończeniu gry
        closeConnections();
    }

    @Override
    public Player playGame(Player playerX, Player playerO) {
        Player currentPlayer = playerX;
        boolean gameRunning = true;

        while (gameRunning) {
            // Wyświetlanie aktualnej planszy
            printArr();

            try {
                if (currentPlayer instanceof RemotePlayer) {
                    // Odbieranie ruchu od zdalnego gracza
                    String move = receiveMove();
                    processMove(move, currentPlayer.getSymbol());
                } else {
                    // Serwer (playerX) wykonuje ruch lokalnie
                    String move = playerX.playTurn();
                    processMove(move, currentPlayer.getSymbol());

                    // Wysłanie ruchu do klienta
                    sendBoardState();
                }

                // Sprawdzenie stanu gry (np. wygrana, remis)
                if (checkWin(currentPlayer.getSymbol())) {
                    System.out.println("Player " + currentPlayer.getSymbol() + " wins!");
                    gameRunning = false;
                } else if (checkDraw()) {
                    System.out.println("Draw!");
                    gameRunning = false;
                }

                // Zmiana gracza
                currentPlayer = (currentPlayer == playerX) ? playerO : playerX;

            } catch (IOException e) {
                e.printStackTrace();
                gameRunning = false;
            }
        }

        return null; // Koniec gry
    }

    // Przetwarzanie ruchu - zakładamy format "row,col"
    private void processMove(String move, Symbol symbol) {
        String[] parts = move.split(",");
        int row = Integer.parseInt(parts[0]);
        int col = Integer.parseInt(parts[1]);
        setBoard3x3(row, col, symbol);
    }

    // Wysyłanie stanu planszy do klienta
    public void sendBoardState() throws IOException {
        for (int i = 0; i < 3; i++) {
            StringBuilder row = new StringBuilder();
            for (int j = 0; j < 3; j++) {
                row.append(board3x3[i][j].toString()).append(" ");
            }
            out.println(row.toString());
        }
        out.println("END"); // Oznaczenie końca przesyłania stanu planszy
    }

    // Odbieranie ruchu od klienta
    public String receiveMove() throws IOException {
        return in.readLine(); // Odbieranie ruchu w formacie "row,col"
    }

    // Zamknięcie połączeń sieciowych
    public void closeConnections() throws IOException {
        in.close();
        out.close();
        clientSocket.close();
        serverSocket.close();
    }

    private boolean checkWin(Symbol symbol) {
        for (int i = 0; i < 3; i++) {
            if (board3x3[i][0] == symbol && board3x3[i][1] == symbol && board3x3[i][2] == symbol) {
                return true; // Wiersz
            }
            if (board3x3[0][i] == symbol && board3x3[1][i] == symbol && board3x3[2][i] == symbol) {
                return true; // Kolumna
            }
        }
        // Przekątne
        if (board3x3[0][0] == symbol && board3x3[1][1] == symbol && board3x3[2][2] == symbol) {
            return true;
        }
        return board3x3[0][2] == symbol && board3x3[1][1] == symbol && board3x3[2][0] == symbol;
    }

    public boolean checkDraw() {
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                if (board3x3[i][j] == Symbol.E) {
                    return false; // Jeśli znajdziemy puste pole, nie jest jeszcze remis
                }
            }
        }
        return true;
    }
}
