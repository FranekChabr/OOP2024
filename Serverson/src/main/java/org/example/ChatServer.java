package org.example;

import java.io.*;
import java.net.*;
import java.time.LocalDateTime;
import java.util.*;

public class ChatServer {
    private ServerSocket serverSocket;
    private Set<ClientHandler> clientHandlers;
    private DatabaseManager dbManager;

    public ChatServer(int port) throws IOException {
        serverSocket = new ServerSocket(port);
        clientHandlers = new HashSet<>();
        dbManager = new DatabaseManager();
        dbManager.initializeDatabase(); // Inicjalizacja bazy danych
        System.out.println("Serwer uruchomiony na porcie: " + port);
    }

    // Rozpoczęcie nasłuchiwania klientów
    public void start() {
        new Thread(() -> listenForClients()).start();
        handleBroadcastFromConsole();
    }

    // Obsługa nowych klientów
    private void listenForClients() {
        try {
            while (true) {
                Socket clientSocket = serverSocket.accept();
                System.out.println("Nowy klient połączony: " + clientSocket.getInetAddress());

                ClientHandler clientHandler = new ClientHandler(clientSocket, this);
                clientHandlers.add(clientHandler);
                new Thread(clientHandler).start();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    // Odczytywanie wiadomości z konsoli serwera i wysyłanie broadcastu
    private void handleBroadcastFromConsole() {
        BufferedReader keyboard = new BufferedReader(new InputStreamReader(System.in));
        String message;

        try {
            while ((message = keyboard.readLine()) != null) {
                if (message.equalsIgnoreCase("exit")) {
                    System.out.println("Serwer zamyka się...");
                    stop();
                    break;
                }
                broadcastMessage("Serwer: " + message, null);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    // Zatrzymanie serwera
    public void stop() throws IOException {
        for (ClientHandler clientHandler : clientHandlers) {
            clientHandler.closeConnection();
        }
        serverSocket.close();
    }

    // Wysyłanie wiadomości do wszystkich klientów (broadcast)
    public synchronized void broadcastMessage(String message, ClientHandler sender) {
        for (ClientHandler clientHandler : clientHandlers) {
            if (sender == null || clientHandler != sender) {
                clientHandler.sendMessage(message);
            }
        }
    }

    // Usunięcie klienta z listy
    public synchronized void removeClient(ClientHandler clientHandler) {
        clientHandlers.remove(clientHandler);
    }

    // Zapisuje użytkownika do bazy danych po połączeniu
    public void saveUserToDatabase(String username) {
        LocalDateTime connectionTime = LocalDateTime.now();
        dbManager.addUserToDatabase(username, connectionTime);
    }
}