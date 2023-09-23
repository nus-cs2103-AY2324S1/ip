package storage;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

import task.Deadline;
import task.Event;
import task.Task;
import task.TaskList;
import task.Todo;
import ui.Ui;

/**
 * Class responsible for loading saved data from a file.
 */
public class BocchiLoader {
    private static final String DEFAULT_PATH = "./data/bocchi.txt";
    private static final int INDEX_TYPE = 0;
    private static final int INDEX_MARKED = 1;
    private static final int INDEX_NAME = 2;
    private static final int INDEX_DEADLINE = 3;
    private static final int INDEX_START_TIME = 3;
    private static final int INDEX_END_TIME = 4;
    private final String filePath;

    /**
     * Constructs a BocchiLoader with the default file path.
     */
    public BocchiLoader() {
        this.filePath = DEFAULT_PATH;
    }

    /**
     * Constructs a BocchiLoader with a specified file path.
     *
     * @param filePath The path to the file from which to load data.
     */
    public BocchiLoader(String filePath) {
        this.filePath = filePath;
    }

    /**
     * Parses data from a string and creates a Task object.
     *
     * @param data The string containing task data.
     * @return A Task object representing the parsed data.
     * @throws Exception If the data cannot be properly parsed.
     */
    private Task parseDataToEvent(String data) throws Exception {
        String[] tokens = data.split("\\|");
        String type = tokens[INDEX_TYPE].trim();
        String name = tokens[INDEX_NAME].trim();
        Boolean marked = tokens[INDEX_MARKED].trim().equals("1");

        switch (type) {
        case "T":
            return new Todo(name, marked);
        case "D":
            return new Deadline(name, tokens[INDEX_DEADLINE].trim(), marked);
        case "E":
            String start = tokens[INDEX_START_TIME].trim();
            String end = tokens[INDEX_END_TIME].trim();
            return new Event(name, start, end, marked);
        default:
            throw new Exception("File might be corrupted, please check input file.");
        }
    }

    /**
     * Loads a TaskList from a file.
     *
     * @param ui The user interface component for displaying messages.
     * @return The loaded TaskList.
     */
    public TaskList loadTaskList(Ui ui) {
        try {
            File f = new File(this.filePath); // create a File for the given file path
            Scanner s = new Scanner(f); // create a Scanner using the File as the source
            TaskList taskList = new TaskList();

            while (s.hasNext()) {
                try {
                    Task task = parseDataToEvent(s.nextLine());
                    taskList = taskList.addTask(task);
                } catch (Exception e) {
                    System.out.println(e.getMessage());
                }
            }
            ui.loadSuccessful();
            return taskList;
        } catch (FileNotFoundException e) {
            ui.loadUnsuccessful();
            return new TaskList();
        }
    }
}
