package duke;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.ArrayList;

/**
 * Handles the saving and loading of tasks to and from a file in a directory.
 */
public class Storage {
    private final String fileName = "data.txt";
    /**
     * Saves the provided list of tasks to a file in the program's directory.
     *
     * @param tasks The list of tasks to be saved.
     */
    public void saveTasksToFile(ArrayList<Task> tasks) {
        try (ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(getFilePath()))) {
            oos.writeObject(tasks);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * Loads tasks from a file in the program's directory and returns them as an ArrayList.
     *
     * @return An ArrayList containing the loaded tasks.
     */
    @SuppressWarnings("unchecked")
    public ArrayList<Task> loadTasksFromFile() {
        ArrayList<Task> tasks = new ArrayList<>();
        File file = new File(getFilePath());
        if (file.exists() && file.length() > 0) {
            try (ObjectInputStream ois = new ObjectInputStream(new FileInputStream(getFilePath()))) {
                tasks = (ArrayList<Task>) ois.readObject();
            } catch (IOException | ClassNotFoundException e) {
                e.printStackTrace();
            }
        }
        return tasks;
    }

    /**
     * Get the full path to the data file, considering the JAR file location.
     *
     * @return The full path to the data file.
     */
    private String getFilePath() {
        String currentDirectory = System.getProperty("user.dir");
        return currentDirectory + File.separator + "data" + File.separator + fileName;
    }
}
