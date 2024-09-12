package org.example;

import java.io.IOException;

public class Main {
    public static void main(String[] args) {
        try {
            ChatClient client = new ChatClient("localhost", 12345); // Połącz się z serwerem na "localhost" i porcie 12345
            client.start(); // Rozpocznij komunikację z serwerem
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}