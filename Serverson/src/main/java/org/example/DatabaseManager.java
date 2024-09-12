package org.example;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDateTime;
import java.time.ZoneOffset;

public class DatabaseManager {

    private static final String DATABASE_URL = "jdbc:sqlite:connected_users.db";

    // Inicjalizacja bazy danych (tworzenie tabeli, jeśli nie istnieje)
    public void initializeDatabase() {
        String createTableSQL = """
                CREATE TABLE IF NOT EXISTS users (
                    id INTEGER PRIMARY KEY AUTOINCREMENT,
                    username TEXT NOT NULL,
                    connection_time TEXT NOT NULL
                );
                """;

        try (Connection conn = connect(); PreparedStatement stmt = conn.prepareStatement(createTableSQL)) {
            stmt.executeUpdate();
            System.out.println("Tabela użytkowników została zainicjalizowana.");
        } catch (SQLException e) {
            System.out.println("Błąd podczas inicjalizacji bazy danych: " + e.getMessage());
        }
    }

    // Dodawanie użytkownika do bazy danych
    public void addUserToDatabase(String username, LocalDateTime connectionTime) {
        String insertSQL = "INSERT INTO users (username, connection_time) VALUES (?, ?);";

        try (Connection conn = connect(); PreparedStatement stmt = conn.prepareStatement(insertSQL)) {
            stmt.setString(1, username);
            stmt.setString(2, connectionTime.toString());
            stmt.executeUpdate();
            System.out.println("Dodano użytkownika: " + username + " o godzinie " + connectionTime);
        } catch (SQLException e) {
            System.out.println("Błąd podczas dodawania użytkownika do bazy danych: " + e.getMessage());
        }
    }

    // Wyświetlanie użytkowników i czasów ich dołączenia w milisekundach
    public void displayUsers() {
        String selectSQL = "SELECT username, connection_time FROM users;";

        try (Connection conn = connect(); PreparedStatement stmt = conn.prepareStatement(selectSQL); ResultSet rs = stmt.executeQuery()) {
            while (rs.next()) {
                String username = rs.getString("username");
                String connectionTimeStr = rs.getString("connection_time");

                // Przekształcenie connectionTimeStr do LocalDateTime i obliczenie czasu w milisekundach
                LocalDateTime connectionTime = LocalDateTime.parse(connectionTimeStr);
                long timeInMillis = connectionTime.toInstant(ZoneOffset.UTC).toEpochMilli();

                System.out.println("Użytkownik: " + username + ", Czas dołączenia: " + timeInMillis + " ms");
            }
        } catch (SQLException e) {
            System.out.println("Błąd podczas wyświetlania użytkowników: " + e.getMessage());
        }
    }

    // Nawiązanie połączenia z bazą danych
    private Connection connect() throws SQLException {
        return DriverManager.getConnection(DATABASE_URL);
    }
}
