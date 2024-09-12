# Gotowe schematy baz danych

### tworzenie połączenia DB
```Java
Connection conn;
    public Database(){
        try {
            conn = DriverManager.getConnection("jdbc:sqlite:baza_danych.db");
        } catch (SQLException e) {
           System.err.println(e.getMessage());
        }
        this.conn = null;
    }
```
### tworzenie połączenia i stworzenie tabeli w DB
```Java

  Connection connection = DriverManager.getConnection("jdbc:sqlite:sample.db");
        if(connection != null) {
            String sql = "CREATE TABLE IF NOT EXISTS images (" +
                    "id INTEGER PRIMARY KEY AUTOINCREMENT," +
                    "path TEXT NOT NULL," +
                    "size TEXT NOT NULL," +
                    "delay INTEGER NOT NULL" +
                    ");"
            Statement statement = connection.createStatement();
            statement.execute(sql);
```
### tworzenie tabeli w bazie danych
```Java
 public void createTable(){
        try {
            Statement statement = conn.createStatement();
            statement.execute("CREATE TABLE IF NOT EXISTS entry (token TEXT NOT NULL, x INTEGER NOT NULL, y INTEGER NOT NULL, color TEXT NOT NULL, timestamp TEXT NOT NULL);");
        }catch(SQLException exception){
            System.err.println(exception.getMessage());
        }
    }
```
### wkładanie wartości do bazy danych
```Java
 public void insertPixelData(Pixel pixel){
        if(conn != null){
            try {
                PreparedStatement statement = conn.prepareStatement("INSERT INTO entry (token, x, y, color, timestamp) VALUES(?, ?, ?, ?, ?);");

                statement.setString(1, pixel.getToken());
                statement.setInt(2, pixel.getX());
                statement.setInt(3, pixel.getY());
                statement.setString(4, "#"+Integer.toHexString(pixel.getColor().getRGB()));
                statement.setTimestamp(5, new Timestamp(System.currentTimeMillis()));

                statement.execute();
            }catch(SQLException exception){
                System.err.println(exception.getMessage());
            }
        }
    }
```
### pobieranie wartości z bazy danych
```Java
import java.sql.*;
try (
    Connection connection = DriverManager.getConnection("jdbc:sqlite:sample.db");
    Statement statement = connection.createStatement();
) {
    statement.executeUpdate("DROP TABLE IF EXISTS person");
    statement.executeUpdate("CREATE TABLE PERSON (id INTEGER, name STRING)");
    statement.executeUpdate("INSERT INTO PERSON VALUES(1, 'leo')");
    statement.executeUpdate("INSERT INTO PERSON VALUES(2, 'yui')");
    ResultSet rs = statement.executeQuery("SELECT * FROM person");
    while (rs.next()) {
        System.out.print("name = " + rs.getString("name"));
        System.out.print("id = " + rs.getInt("id"));
    }
} catch (SQLException e) {
e.printStackTrace(System.err); }
```
