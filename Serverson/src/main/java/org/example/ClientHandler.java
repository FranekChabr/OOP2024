package org.example;

import java.io.*;
import java.net.*;

public class ClientHandler implements Runnable {
    private Socket socket;
    private BufferedReader in;
    private PrintWriter out;
    private String username;
    private ChatServer server;

    public ClientHandler(Socket socket, ChatServer server) {
        this.socket = socket;
        this.server = server;
    }

    @Override
    public void run() {
        try {
            in = new BufferedReader(new InputStreamReader(socket.getInputStream()));
            out = new PrintWriter(socket.getOutputStream(), true);

            // Logowanie klienta
            out.println("Podaj swoją nazwę użytkownika:");
            username = in.readLine();
            System.out.println(username + " połączony.");

            // Zapisz użytkownika do bazy danych
            server.saveUserToDatabase(username);

            out.println("Witaj, " + username + "! Możesz wysłać wiadomość.");

            String message;
            while ((message = in.readLine()) != null) {
                System.out.println(username + ": " + message);

                if (message.equalsIgnoreCase("exit")) {
                    break;
                } else if (message.startsWith("/broadcast ")) {
                    String broadcastMessage = message.substring(11);
                    server.broadcastMessage("Broadcast od " + username + ": " + broadcastMessage, this);
                } else {
                    out.println("Serwer: " + message);
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            closeConnection();
        }
    }

    // Zamykanie połączenia z klientem
    public void closeConnection() {
        try {
            socket.close();
            server.removeClient(this);
            System.out.println(username + " rozłączył się.");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    // Wysyłanie wiadomości do klienta
    public void sendMessage(String message) {
        out.println(message);
    }
}