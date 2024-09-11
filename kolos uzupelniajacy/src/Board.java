public abstract class Board {
    public Symbol[][] board3x3 = new Symbol[3][3];

    public Board() {
        poczatek();
    }

    public void poczatek() {
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                board3x3[i][j] = Symbol.E; // Domyślnie każdy element jest pusty
            }
        }
    }

    public void printArr() {
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

    public abstract Player playGame(Player playerX, Player playerO);

    public void setBoard3x3(int x, int y, Symbol symbol) {
        this.board3x3[x][y] = symbol;
    }
}
