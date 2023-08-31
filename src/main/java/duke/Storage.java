package duke;

import java.io.*;

import java.util.ArrayList;


/**
 * Handles the saving and loading of tasks to and from a file in a directory.
 */
public class Storage {
    java.nio.file.Path path = java.nio.file.Paths.get( "./","src", "main", "data", "duke.txt");
    private final boolean fileExists = java.nio.file.Files.exists(path);
    File f = new File(String.valueOf(path));

    /**
     * Saves the provided list of tasks to the specified file path.
     *
     * @param tasks The list of tasks to be saved.
     * @param filePath The path of the file to save tasks to.
     */
    public static void saveTasksToFile(ArrayList<Task> tasks, String filePath) {
        try (ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(filePath))) {
            oos.writeObject(tasks);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * Loads tasks from the specified file path and returns them as an ArrayList.
     *
     * @param filePath The path of the file to load tasks from.
     * @return An ArrayList containing the loaded tasks.
     */
    @SuppressWarnings("unchecked")
    // data/duke.txt will only contain and ArrayList of Tasks
    public static ArrayList<Task> loadTasksFromFile(String filePath) {
        ArrayList<Task> tasks = new TaskList();
        File file = new File(filePath);
        if (file.length() > 0) {
            try (ObjectInputStream ois = new ObjectInputStream(new FileInputStream(filePath))) {
                tasks = (ArrayList<Task>) ois.readObject();
            } catch (IOException | ClassNotFoundException e) {
                e.printStackTrace();
            }
        }
        return tasks;
    }

    /**
     * Retrieves whether the file exists or not.
     *
     * @return `true` if the file exists, `false` otherwise.
     */
    public boolean getFileExists() {
        return fileExists;
    }
}
