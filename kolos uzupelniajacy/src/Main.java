//import java.util.Scanner;
//
//public class Main {
//    public static void main(String[] args) {
//        // Tworzymy planszę gry
//        LocalBoard board = new LocalBoard();
//
//        // Tworzymy graczy
//        Player playerX = new HumanPlayer(Symbol.X);
//        Player playerO = new HumanPlayer(Symbol.O);
//
//        // Rozpoczynamy grę
//        board.playGame(playerX, playerO);
//    }
//}

import java.io.IOException;

public static void main(String[] args) {
    if (args.length != 2) {
        System.out.println("Usage: java GameClient <host> <port>");
        return;
    }

    String host = args[0];
    int port;

    try {
        port = Integer.parseInt(args[1]);
    } catch (NumberFormatException e) {
        System.out.println("Invalid port number.");
        return;
    }

    try {
        GameClient client = new GameClient(host, port);
        client.start();
    } catch (IOException e) {
        System.err.println("An error occurred while trying to connect to the server.");
        e.printStackTrace();
    } finally {
        System.out.println("GameClient has finished execution.");
    }
}



