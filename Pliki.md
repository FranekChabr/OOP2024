# Main 
```java
public class Main {
    public static void main(String[] args) {
        FileCreator creator = new FileCreator();
        FileReaderClass reader = new FileReaderClass();
        FileEditor editor = new FileEditor();

        String fileName = "test1a.txt";

        // Tworzenie nowego pliku z początkową zawartością
        creator.createFile(fileName, "This is the first line of the file.\n");

        // Odczytanie zawartości pliku
        System.out.println("Reading the file:");
        reader.readFile(fileName);

        // Dodanie nowej zawartości do pliku
        editor.appendToFile(fileName, "This is the second line.\n");

        // Ponowne odczytanie zawartości pliku
        System.out.println("Reading the file after editing:");
        reader.readFile(fileName);
    }
}
```

# FileCreator klasa
```java
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

public class FileCreator {
    public void createFile(String fileName, String content) {
        try {
            File file = new File(fileName);
            if (file.createNewFile()) {
                System.out.println("File created: " + file.getName());
                FileWriter writer = new FileWriter(file);
                writer.write(content);
                writer.close();
            } else {
                System.out.println("File already exists.");
            }
        } catch (IOException e) {
            System.out.println("An error occurred.");
            e.printStackTrace();
        }
    }
}
```
# FileReaderClass klasa
```java
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
public class FileReaderClass { //class dodane z powodu zbierznosci nazw z podstawową klasą Javy "FileReader"
    public void readFile(String fileName) {
        try (BufferedReader reader = new BufferedReader(new FileReader(fileName))) {
            String line;
            while ((line = reader.readLine()) != null) {
                System.out.println(line);
            }
        } catch (IOException e) {
            System.out.println("An error occurred.");
            e.printStackTrace();
        }
    }
}
```
# FileEditor klasa
```java
import java.io.FileWriter;
import java.io.IOException;
public class FileEditor {
    public void appendToFile(String fileName, String content) {
        try (FileWriter writer = new FileWriter(fileName, true)) {  // True means append mode
            writer.write(content);
            System.out.println("Successfully appended to the file.");
        } catch (IOException e) {
            System.out.println("An error occurred.");
            e.printStackTrace();
        }
    }
}
```


