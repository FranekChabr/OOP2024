# Gotowe schematy

### tworzenie połączzenia DB
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
