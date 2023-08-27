import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.util.Scanner;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;

public class Storage {

    private static final String FOLDER_PATH = "data";
    private static final String FILE_PATH = "data/duke.txt";
    private static final File FOLDER = new File(FOLDER_PATH);
    private static final File FILE = new File(FILE_PATH);

    public static void readFile() {
        try {
            Scanner sc = new Scanner(FILE);
            while (sc.hasNext()) {
                System.out.println(sc.nextLine());
            }
            sc.close();
        } catch (FileNotFoundException e) {
            createFile();
        }
    }

    private static void createFile() {
        if (!FOLDER.exists()) {
            FOLDER.mkdirs();
        }
        try {
            FILE.createNewFile();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public static void writeFile(String text, boolean isAppend) {
        try {
            FileWriter fw = new FileWriter(FILE_PATH, isAppend);
            fw.write(text);
            fw.close();
        } catch (IOException e) {
            createFile();
            writeFile(text, isAppend);
        }
    }
}
