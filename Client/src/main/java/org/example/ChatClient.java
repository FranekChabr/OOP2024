package org.example;
import java.io.*;
import java.net.*;

public class ChatClient {
    private Socket socket;
    private BufferedReader in;
    private PrintWriter out;
    private BufferedReader keyboard;

    public ChatClient(String host, int port) throws IOException {
        socket = new Socket(host, port);
        in = new BufferedReader(new InputStreamReader(socket.getInputStream()));
        out = new PrintWriter(socket.getOutputStream(), true);
        keyboard = new BufferedReader(new InputStreamReader(System.in));

        System.out.println("Połączono z serwerem " + host + " na porcie " + port);
    }

    // Rozpocznij komunikację z serwerem
    public void start() {
        // Wątek do odbierania wiadomości z serwera
        new Thread(() -> listenForServerMessages()).start();

        // Wysyłanie wiadomości z klawiatury do serwera
        handleUserInput();
    }

    // Odbieranie wiadomości od serwera
    private void listenForServerMessages() {
        try {
            String messageFromServer;
            while ((messageFromServer = in.readLine()) != null) {
                System.out.println(messageFromServer);
            }
        } catch (IOException e) {
            System.out.println("Połączenie z serwerem zostało zakończone.");
        }
    }

    // Odczytywanie wiadomości od użytkownika i wysyłanie ich do serwera
    private void handleUserInput() {
        String userInput;
        try {
            while ((userInput = keyboard.readLine()) != null) {
                out.println(userInput);
                if (userInput.equalsIgnoreCase("exit")) {
                    close();
                    break;
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    // Zamykanie połączenia
    public void close() {
        try {
            socket.close();
            System.out.println("Połączenie zamknięte.");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}