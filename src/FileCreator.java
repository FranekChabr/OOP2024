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
