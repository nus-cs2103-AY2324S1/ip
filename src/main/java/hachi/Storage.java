package hachi;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

import exceptions.HachiException;

/**
 * Handles all the tasks related to saving and loading data from the local storage.
 * Provides methods to obtain information about the task list from the file, as well as for updating the file.
 */
public class Storage {

    private static String DEFAULT_PATH = "./data/tasks.txt";

    private Path currentPath;

    /**
     * Overloaded constructor for the Storage class.
     * Initialises the currentPath variable using the default path.
     */
    public Storage() {
        this(DEFAULT_PATH);
    }

    /**
     * Overloaded constructor for the Storage class.
     * Initialises the currentPath variable, taking in the filePath variable if passed in.
     * @param filePath
     */
    public Storage(String filePath) {
        Path path = Paths.get(filePath);
        currentPath = path;
    }

    /**
     * Reads the text from the task text file and returns it as a task list.
     * Creates the directory and file for storage if it doesn't already exist.
     * @return The saved task list in storage, as a TaskList object
     * @throws HachiException
     */
    public TaskList getTaskList() throws HachiException {
        if (!Files.exists(currentPath)) {
            try {
                Files.createDirectories(currentPath.getParent());
                Files.createFile(currentPath);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        try {
            return Parser.parseTaskList(Files.readAllLines(currentPath));
        } catch (IOException e) {
            throw new HachiException("Unable to read file");
        }
    }


    private static void appendToFile(File file, String textToAdd) throws IOException {
        FileWriter fw = new FileWriter(file, true);
        fw.write(textToAdd);
        fw.close();
    }

    /**
     * Updates the task list file in storage with the new task list.
     * Takes in a taskList and converts it to text form, and writes it to the storage file.
     * @param taskList The updated task list to be saved to storage
     */
    public void updateTaskFile(TaskList taskList) {
        File taskPath = currentPath.toFile();
        // clear file first
        try {
            new FileWriter(taskPath).close();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

        // add every task in current task list
        taskList.iter(task -> {
            try {
                appendToFile(taskPath, task.toData() + "\n");
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        });
    }


}
