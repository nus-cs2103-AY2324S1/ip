package duke;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;

public class Storage {
    private File storage;
    private Scanner storageScanner;

    public Storage() {
    }
    public void init(String pathName) throws IOException {
        storage = new File(pathName);
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
            writer.write("\n");
        }
        writer.close();
    }

    public ArrayList<String> loadFromDisk() throws IOException {
        ArrayList<String> result = new ArrayList<>();
        storageScanner = new Scanner(storage);
        while (storageScanner.hasNextLine()) {
            result.add(storageScanner.nextLine());
        }
        return result;
    }
}
