package jarvis.storage;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;

import jarvis.exceptions.InvalidTaskFormatException;
import jarvis.parser.Parser;
import jarvis.tasks.Task;

/**
 * Storage class is responsible for storing and loading tasks from file destination.
 */
public class Storage {
    /**
     * Assign user directory as the directory and file destination.
     *
     * Method declaration inspired by @Tan Kerway
     * https://github.com/kiwibang/ip
     */
    private static final String FILE_DIR = System.getProperty("user.dir") + "/data";
    private static final String FILE_PATH = System.getProperty("user.dir") + "/data/jarvis.txt";

    /**
     * Initializes a new instance of the Storage class and creates necessary directories and files.
     */
    public Storage() {
        File fileDir = new File(FILE_DIR);
        if (!fileDir.exists()) {
            fileDir.mkdir();
            assert fileDir.exists() : "File Dir creation was not successful";
        }

        File file = new File(FILE_PATH);
    }

    /**
     * Saves a list of tasks to the file destination.
     *
     * @param tasks An ArrayList of task to be saved.
     */
    public void saveTasks(ArrayList<Task> tasks) {
        try {
            BufferedWriter writer = new BufferedWriter(new FileWriter(FILE_PATH));
            for (Task task : tasks) {
                System.out.println(task);
                writer.write(task.toString());
                writer.newLine();
            }
            writer.close();
        } catch (IOException e) {
            System.err.println(e.getMessage());
        }
    }

    /**
     * Loads tasks from the file destination.
     *
     * @return An ArrayList of tasks loaded from the storage file.
     */
    public ArrayList<Task> loadTasks() {
        ArrayList<Task> tasks = new ArrayList<>();
        try (BufferedReader reader = new BufferedReader(new FileReader(FILE_PATH))) {
            String nextLine;
            while ((nextLine = reader.readLine()) != null) {
                Task task = Parser.parseStringToTask(nextLine);
                tasks.add(task);
                assert tasks.contains(task);
            }
        } catch (IOException e) {
            System.err.println("Unable to load tasks" + FILE_PATH);
        } catch (InvalidTaskFormatException e) {
            System.err.println("Invalid Task Format when loading tasks");
        }
        return tasks;
    }
}
