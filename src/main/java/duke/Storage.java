package duke;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;

import duke.task.Task;

/**
 * The `Storage` class handles loading and saving tasks to a data file.
 */
public class Storage {

    private static final String FOLDER_PATH = "./data";

    private static final String DATA_FILE_PATH = "./data/duke.txt";

    private String filePath;

    /**
     * Constructs a `Storage` object with the default data file path.
     */
    public Storage() {
        this.filePath = DATA_FILE_PATH;
    }

    /**
     * Constructs a `Storage` object with a custom data file path.
     *
     * @param filePath The file path to the data file.
     */
    public Storage(String filePath) {
        this.filePath = filePath;
    }

    /**
     * Loads tasks from the data file.
     *
     * @return An ArrayList of tasks loaded from the data file.
     * @throws DukeException If there is an error loading tasks or the data file is corrupted.
     */
    public ArrayList<Task> loadTask() throws DukeException {
        ArrayList<Task> loadedTasks = new ArrayList<>();

        try {
            File folder = new File(FOLDER_PATH);
            if (!folder.exists()) {
                folder.mkdirs();
            }

            File file = new File(filePath);
            if (!file.exists()) {
                return loadedTasks;
            }

            Scanner scanner = new Scanner(file);
            while (scanner.hasNextLine()) {
                String line = scanner.nextLine();
                String[] parts = line.split("]", 3);
                String taskType = parts[0];
                boolean isDone = parts[1].equals("[X");
                String description = parts[2];
                description = description.replace("(", "");
                description = description.replace(")", "");
                Task task = Parser.parse(taskType, description, isDone);
                loadedTasks.add(task);
            }
            scanner.close();
        } catch (IOException e) {
            throw new DukeException("Corrupted data file: " + e.getMessage());
        }
        return loadedTasks;
    }
    /**
     * Saves tasks to the data file.
     *
     * @param tasks The ArrayList of tasks to be saved.
     * @throws DukeException If there is an error saving tasks to the data file.
     */
    public void saveTasks(ArrayList<Task> tasks) throws DukeException {
        try {
            File folder = new File(FOLDER_PATH);
            if (!folder.exists()) {
                folder.mkdirs();
            }

            File file = new File(filePath);
            if (!file.exists()) {
                file.createNewFile();
            }

            FileWriter fw = new FileWriter(filePath);

            for (int i = 0; i < tasks.size(); i++) {
                fw.write((tasks.get(i)).toSave() + "\n");
            }

            fw.close();
        } catch (IOException e) {
            throw new DukeException("Error saving tasks to data file: " + e.getMessage());
        }
    }
}
