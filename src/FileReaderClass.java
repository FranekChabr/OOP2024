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
