package Duke;
import java.io.File;
import java.io.IOException;
import java.io.FileWriter;
import java.util.Scanner;
import java.util.ArrayList;

public class Storage {
    File storage;
    Scanner storageScanner;

    public Storage() {
    }
    public void init(String pathname) throws IOException {
        storage = new File(pathname);
        storage.createNewFile();
    }
    public void clear() throws IOException {
        storage.createNewFile();
        writeToDisk(new ArrayList<>());
    }

    public void writeToDisk(ArrayList<String> list) throws IOException {
        FileWriter writer = new FileWriter(storage);
        for (String obj : list) {
            writer.write(obj);
        }
        writer.close();
    }

    public ArrayList<String> loadFromDisk() throws IOException{
        ArrayList<String> result = new ArrayList<>();
        storageScanner = new Scanner(storage);
        while (storageScanner.hasNextLine()) {
            result.add(storageScanner.nextLine());
        }
        return result;
    }
}
