package Duke;
import java.io.File;
import java.io.IOException;
import java.io.FileWriter;
import java.util.Scanner;

public class Storage {
    File storage;
    FileWriter writer;
    Scanner storageScanner;
    public Storage(String pathname) {
        try {
            storage = new File(pathname);
            storage.createNewFile();
        } catch (IOException e) {
        }
    }
    public void writeToDisk(TaskList tasklist) {

    }
}
