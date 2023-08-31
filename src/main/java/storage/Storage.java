package storage;

import java.util.ArrayList;

import tasklist.TaskList;
import tasks.Task;

/**
 * The Storage class provides methods to interact with
 * storage files for reading and writing task data.
 */
public class Storage {
    private static String path;

    private DataReader dataReader;

    private DataWriter dataWriter;

    /**
     * Constructs a Storage instance with the specified file path.
     *
     * @param path The file path of the storage file to be read from and written to.
     */
    public Storage(String path) {
        Storage.path = path;
        this.dataReader = new DataReader(path);
        this.dataWriter = new DataWriter(path);
    }

    /**
     * Adds a new line to the storage file.
     *
     * @param line The line to be added.
     */
    public static void addLine(String line) {
        DataWriter.addLine(line);
    }

    /**
     * Deletes a line from the storage file based on the specified position.
     *
     * @param pos The position of the line to be deleted.
     */
    public static void deleteLine(int pos) {
        DataWriter.deleteLine(pos);
    }

    /**
     * Refreshes the storage file with the tasks from the given TaskList.
     *
     * @param taskList The TaskList containing tasks to be written to the file.
     */
    public static void refresh(TaskList taskList) {
        DataWriter.refresh(taskList);
    }

    /**
     * Reads task data from a storage file and returns them as an ArrayList of Task objects.
     *
     * @param fileName The name of the storage file to be read.
     * @return An ArrayList of Task objects representing the tasks read from the file.
     */
    public static ArrayList<Task> readFileToTasksLists(String fileName) {
        return DataReader.readTasksFromFile();
    }
}
