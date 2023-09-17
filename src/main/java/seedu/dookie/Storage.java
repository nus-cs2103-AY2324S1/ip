package seedu.dookie;
import java.io.*;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;

/**
 * Encapsulates the Storage class.
 * The Storage class deals with saving and loading information to a specified file.
 */
public class Storage {
    protected String filePath;

    /**
     * Creates a Storage instance.
     *
     * @param filePath The relative path to the file used to store data.
     */
    public Storage(String filePath) {
        this.filePath = filePath;
    }

    /**
     * Reads the data in the dookie.txt file, unserializes it and returns an arraylist representing
     * the saved tasks.
     *
     * @return An ArrayList<Task> containing the saved tasks accumulated.
     * @throws InvalidDataFormatException if the format of the data in the saved file is incorrect.
     */
    public ArrayList<Task> load() throws InvalidDataFormatException {
        ArrayList<Task> tasks = new ArrayList<>();

        String folderPath = "./data/";
        String filePath = "./data/dookie.txt";

        try {
            // Check if the folder exists, create if not
            Path folder = Paths.get(folderPath);
            if (!Files.exists(folder)) {
                Files.createDirectories(folder);
            }


            // Check if the file exists, create if not
            Path file = Paths.get(filePath);
            if (!Files.exists(file)) {
                Files.createFile(file);
            }

            // If the dooike.txt file is empty, return an empty task arraylist
            if (Files.size(file) == 0) {
                return tasks;
            }

            // Throws StreamCorruptedException when data format is unserializable
            ObjectInputStream inputStream = new ObjectInputStream(new FileInputStream(filePath));
            tasks = (ArrayList<Task>) inputStream.readObject();

            // Throws exception if data in dookie.txt is not an ArrayList<Task>
            if (tasks instanceof ArrayList<?>) {
                tasks = (ArrayList<Task>) tasks;
            } else {
                throw new InvalidDataFormatException();
            }
        } catch (StreamCorruptedException e) {
            throw new InvalidDataFormatException();
        } catch (IOException e) {
            throw new InvalidDataFormatException();
        } catch (ClassNotFoundException e) {
            throw new InvalidDataFormatException();
        }
        return tasks;
    }

    /**
     * Removes all data from the dookie.txt file. Takes the task arraylist argument, serializes it
     * and stores it in the dookie.txt file.
     *
     * @param data The new ArrayList of tasks to be overwritten in the dookie.txt file.
     */
    public void save(ArrayList<Task> data) {
        // Delete all data from dookie.txt file
        try (ObjectOutputStream outputStream = new ObjectOutputStream(new FileOutputStream("./data/dookie.txt", false))) {
            outputStream.writeObject(data);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
