module org.example.test_javafx {
    requires javafx.controls;
    requires javafx.fxml;
    requires java.sql;


    opens org.example.test_javafx to javafx.fxml;
    exports org.example.test_javafx;
}