public class LocalBoard extends Board {

    public void start(Player playerX, Player playerO){
    }

    @Override
    public Player playGame(Player playerX, Player playerO) {
        Player currentPlayer = playerX; // Zakładamy, że gra zaczyna się od gracza X
        boolean gameWon = false;
        boolean draw = false;

        while (!gameWon && !draw) {
            printArr();
            System.out.println(currentPlayer.inform());
            // Zakładam, że metoda playTurn zwraca stringa z informacją o ruchu
            String move = currentPlayer.playTurn();
            // Parsujemy ruch, np. "1,2" oznacza wiersz 1, kolumna 2
            String[] parts = move.split(",");
            int x = Integer.parseInt(parts[0]);
            int y = Integer.parseInt(parts[1]);

            // Sprawdzamy, czy ruch jest poprawny

            if (x < 0 || x >= 3 || y < 0 || y >= 3 || board3x3[x][y] != Symbol.E) {
                System.out.println("Invalid move, try again.");
                continue;
            }

            // Wykonujemy ruch
            setBoard3x3(x, y, currentPlayer.getSymbol());

            // Sprawdzamy, czy gra została wygrana
            gameWon = checkWin(currentPlayer.getSymbol());

            // Sprawdzamy, czy plansza jest pełna (remis)
            draw = checkDraw();

            // Zmieniamy gracza
            currentPlayer = (currentPlayer == playerX) ? playerO : playerX;
        }

        printArr();
        if (gameWon) {
            System.out.println("Player " + currentPlayer.getSymbol() + " wins!");
        } else {
            System.out.println("It's a draw!");
        }

        return currentPlayer; // Zwracamy zwycięzcę lub `null` w przypadku remisu
    }

    private boolean checkWin(Symbol symbol) {
        // Sprawdzamy wiersze, kolumny i przekątne
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

    private boolean checkDraw() {
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
