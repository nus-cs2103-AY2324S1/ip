package duke;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;

/**
 * Represents a file on the disk that contains strings.
 */
public class Storage {
    private File storage;
    private Scanner storageScanner;

    public Storage() {
    }

    /**
     * Initializes the storage file to the specified path name.
     * @param pathName the path name of the file to be associated with this object.
     * @throws IOException if the file does not exist, and we are unable to create a new file in the specified path.
     */
    public void init(String pathName) throws IOException {
        storage = new File(pathName);
        storage.createNewFile();
    }

    /**
     * Clears the file to an empty text if possible.
     * @throws IOException if we cannot clear the file.
     */
    public void clear() throws IOException {
        storage.createNewFile();
        writeToDisk(new ArrayList<>());
    }

    /**
     * Writes to the file given an input.
     * @param list An ArrayList representing what we want to write to the disk.
     * @throws IOException if we cannot write to the file.
     */
    public void writeToDisk(ArrayList<String> list) throws IOException {
        FileWriter writer = new FileWriter(storage);
        for (String obj : list) {
            writer.write(obj);
            writer.write("\n");
        }
        writer.close();
    }

    /**
     * Produces an ArrayList of type String that represents the text file associated with this instance.
     * @return ArrayList of type String that represents the text file associated with this instance.
     * @throws IOException if we cannot read from the file.
     */
    public ArrayList<String> loadFromDisk() throws IOException {
        ArrayList<String> result = new ArrayList<>();
        storageScanner = new Scanner(storage);
        while (storageScanner.hasNextLine()) {
            result.add(storageScanner.nextLine());
        }
        return result;
    }
}
