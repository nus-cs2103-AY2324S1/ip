package duchess;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Scanner;

/**
 * A class to deal with storage of data, like saving and loading task lists.
 */
public class Storage {
    private static final String DATA_DIRECTORY = "./";
    private static final String DATA_FILE = "data.duchess";

    /**
     * Creates an empty save file to store the tasks in. This save file is specified by DATA_DIRECTORY and DATA_FILE.
     */
    public static void createSaveFile() {
        try {
            File file = new File(DATA_DIRECTORY + DATA_FILE);

            // Create a new file if it doesn't already exist.
            file.createNewFile();

        } catch (IOException e) {
            System.out.println(String.format("An error has occurred: %s", e.getMessage()));
        }
    }

    /**
     * Saves the specified ArrayList of tasks to the data file specified by DATA_DIRECTORY and DATA_FILE.
     * If the file does not exist, an error will occur.
     *
     * @param tasks - the ArrayList of tasks to be saved.
     */
    public static void saveTasksToFile(TaskList tasks) {
        try {
            File file = new File(DATA_DIRECTORY + DATA_FILE);

            FileWriter fileWriter = new FileWriter(file);

            for (int i = 0; i < tasks.size(); i++) {
                fileWriter.write(tasks.getTask(i).toSaveString());

                // Add a newline unless I just wrote the last item.
                if (i != tasks.size() - 1) {
                    fileWriter.write("\n");
                }
            }

            fileWriter.close();

        } catch (IOException e) {
            System.out.println(String.format("An error has occurred: %s", e.getMessage()));
        }
    }

    /**
     * Loads Tasks from the data file specified by DATA_DIRECTORY and DATA_FILE and returns a TaskList
     * of the loaded tasks.
     *
     * @return a TaskList of tasks that were loaded.
     */
    public static TaskList loadTasksFromFile() {
        TaskList tasks = new TaskList();

        try {
            File file = new File(DATA_DIRECTORY + DATA_FILE);
            Scanner sc = new Scanner(file);

            while (sc.hasNextLine()) {
                Task task = Task.parseSaveString(sc.nextLine());

                if (task != null) {
                    tasks.addTask(task);
                }
            }

            sc.close();

        } catch (IOException e) {
            System.out.println(String.format("An error has occurred: %s", e.getMessage()));
        }

        return tasks;
    }
}
