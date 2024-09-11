import java.util.Scanner;

// Klasa reprezentująca człowieka jako gracza

class HumanPlayer extends Player {
    private final Scanner scanner = new Scanner(System.in);

    public HumanPlayer(Symbol symbol) {
        super(symbol);
    }

    @Override
    public String playTurn() {
        System.out.println("Player " + getSymbol() + ", enter your move (row,col): ");
        return scanner.nextLine(); // Oczekujemy na wejście użytkownika w formacie "row,col"
    }

    @Override
    public String inform() {
        return "Player " + getSymbol() + "'s turn.";
    }

    private void showBoard(Symbol[][] board3x3, Symbol X,Symbol O) {
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                String color;
                switch (board3x3[i][j]) {
                    case X:
                        color = ConsoleColors.RED;
                        break;
                    case O:
                        color = ConsoleColors.GREEN;
                        break;
                    default:
                        color = ConsoleColors.RESET;
                }
                System.out.print(color + board3x3[i][j] + ConsoleColors.RESET + " ");
            }
            System.out.println();
        }
    }


}