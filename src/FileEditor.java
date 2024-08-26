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
