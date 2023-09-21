package oscar.essential;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

import oscar.exception.OscarException;

/**
 * Class to handle loading and saving infos from file.
 */
public class Storage {
    private final String filePath;

    /**
     * Instantiates a storage object.
     *
     * @param filePath Relative location of save file.
     */
    public Storage(String filePath) {
        this.filePath = filePath;
    }

    /**
     * Reads the serialized save file.
     *
     * @return Stream of serialized save file.
     * @throws OscarException Unable to find or read saved file.
     */
    public ObjectInputStream load() throws OscarException {
        File savedFile = new File(filePath);
        boolean hasFile = savedFile.exists();
        boolean isDirectory = savedFile.isDirectory();
        if (!hasFile || isDirectory) {
            throw new OscarException("Sorry! Oscar cannot find a saved file to load.\n");
        }
        try {
            FileInputStream fileInputStream = new FileInputStream(savedFile);
            return new ObjectInputStream(fileInputStream);
        } catch (IOException e) {
            throw new OscarException("Sorry! There is an error loading the saved file.\n");
        }
    }

    /**
     * Saves the info list to a text file after executing a command.
     * Solution adapted by <a href="https://howtodoinjava.com/java/collections/arraylist/
     * serialize-deserialize-arraylist/">...</a>
     *
     * @param infos Current info list.
     * @throws OscarException Unable to serialise info list.
     */
    public void save(ItemList infos) throws OscarException {
        Path parentDir = Paths.get(filePath).getParent();
        try {
            Files.createDirectories(parentDir);
            FileOutputStream fileOutputStream = new FileOutputStream(filePath);
            ObjectOutputStream objectOutputStream = new ObjectOutputStream(fileOutputStream);
            infos.save(objectOutputStream);
        } catch (FileNotFoundException e) {
            throw new OscarException("Sorry! File is not found.\n");
        } catch (IOException e) {
            throw new OscarException("Sorry! There is an issue with your input or output.\n");
        }
    }
}
