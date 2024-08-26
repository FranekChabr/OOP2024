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