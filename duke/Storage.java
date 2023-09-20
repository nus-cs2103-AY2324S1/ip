package duke;
import java.io.*;
import java.util.ArrayList;

/**
 * The Storage class handles the loading and saving of tasks to a file. It provides methods to load tasks from
 * a specified file and save tasks to that file.
 */
public class Storage {
    private String filePath;

    /**
     * Constructs a new Storage object with the specified file path.
     *
     * @param filePath The path to the file where tasks are stored.
     */
    public Storage(String filePath) {
        this.filePath = filePath;
    }

    /**
     * Loads tasks from the specified file.
     *
     * @return An ArrayList of tasks loaded from the file. If the file doesn't exist or an error occurs during
     *         loading, an empty ArrayList is returned.
     * @throws CustomException If an error occurs during loading, including I/O errors or corrupted files.
     */
    public ArrayList<Task> load() throws CustomException {
        ArrayList<Task> tasks;

        File file = new File(filePath);
        if (file.exists()) {
            try (FileInputStream fis = new FileInputStream(file);
                 ObjectInputStream ois = new ObjectInputStream(fis)) {
                // Read the taskArrayList from the file
                tasks = (ArrayList<Task>) ois.readObject();
            } catch (IOException | ClassNotFoundException e) {
                // Handle exceptions, e.g., if the file is corrupted
                tasks = new ArrayList<>();
            }
        } else {
            tasks = new ArrayList<>();
        }
        return tasks;
    }

    /**
     * Saves the provided ArrayList of tasks to the specified file.
     *
     * @param tasks The ArrayList of tasks to be saved to the file.
     * @throws CustomException If an error occurs during saving, including I/O errors or directory creation errors.
     */
    public void save(ArrayList<Task> tasks) throws CustomException {
        File file = new File(filePath);
        File directory = file.getParentFile();

        try {
            // Create the directory if it doesn't exist
            if (!directory.exists()) {
                directory.mkdirs();
            }

            // Create the file if it doesn't exist
            if (!file.exists()) {
                file.createNewFile();
            }

            try (FileOutputStream fos = new FileOutputStream(file);
                 ObjectOutputStream oos = new ObjectOutputStream(fos)) {
                // Write the taskArrayList to the file
                oos.writeObject(tasks);
            } catch (IOException e) {
                System.out.println("Error saving tasks to file!");
                // Throw a CustomException with a custom error message
                throw new CustomException("Error saving tasks to file: " + e.getMessage());
            }
        } catch (IOException e) {
            System.out.println("Error creating the file or directory!");
            // Throw a CustomException with a custom error message
            throw new CustomException("Error creating file or directory: " + e.getMessage());
        }
    }
}

