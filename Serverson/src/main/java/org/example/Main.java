package org.example;

import java.io.IOException;

//TIP To <b>Run</b> code, press <shortcut actionId="Run"/> or
// click the <icon src="AllIcons.Actions.Execute"/> icon in the gutter.
public class Main {
    public static void main(String[] args) {
        try {
            ChatServer server = new ChatServer(12345); // Uruchomienie serwera na porcie 12345
            server.start(); // Start serwera
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}