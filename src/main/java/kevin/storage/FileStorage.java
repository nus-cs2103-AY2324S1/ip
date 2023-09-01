package kevin.storage;

import kevin.taskList.Task;
import kevin.taskList.ToDo;
import kevin.taskList.Event;
import kevin.taskList.Deadline;
import kevin.exception.KevinException;

import java.util.ArrayList;
import java.util.Arrays;

/**
 * A class that is responsible to write to kevin.txt.
 */
public class FileStorage {
    private final Storage storage;
    public static final String FILE_LOCATION = "./data/kevin.txt";

    /**
     * Constructor to initialize FileStorage.
     */
    public FileStorage() {
        this.storage = new Storage();
    }

    /**
     * Makes the file to be written on if it does not exist.
     * @throws KevinException On the detection of errors.
     */
    public void initialize() throws KevinException {
        storage.createFile(FILE_LOCATION);
    }

    /**
     * Adds todo task to the file.
     * @param newToDo This is the todo task to be written to the file.
     * @throws KevinException On the detection of errors.
     */
    public void addToDo(ToDo newToDo) throws KevinException {
       storage.appendFile(FILE_LOCATION, newToDo.toText());
    }

    /**
     * Adds event task to the file.
     * @param newEvent This is the event task to be written to the file.
     * @throws KevinException On the detection of errors.
     */
    public void addEvent(Event newEvent) throws KevinException {
        storage.appendFile(FILE_LOCATION, newEvent.toText());
    }

    /**
     * Overwrites a specific line with the new task.
     * @param newTask This is the new task to be written to the file.
     * @param index This is the line number to be overwritten.
     * @throws KevinException On the detection of errors.
     */
    public void overwriteTask(Task newTask, int index) throws KevinException {
        storage.overwriteLine(FILE_LOCATION, newTask.toText(), index);
    }

    /**
     * Delete a specific line.
     * @param index This is the line number to be deleted.
     * @throws KevinException On the detection of errors.
     */
    public void deleteTask(int index) throws KevinException {
        storage.overwriteLine(FILE_LOCATION, "", index);
    }

    /**
     * Adds deadline task to the file.
     * @param newDeadline This is the deadline task to be written to the file.
     * @throws KevinException On the detection of errors.
     */
    public void addDeadline(Deadline newDeadline) throws KevinException {
        storage.appendFile(FILE_LOCATION, newDeadline.toText());
    }

    /**
     * Divides the String to become ArrayList of String on new line.
     * @param tasks This is the String of tasks to be divided
     * @return Returns an ArrayList of String.
     */
    public ArrayList<String> makeStringToList(String tasks) {
        String[] tasksSplit = tasks.split(System.lineSeparator());
        return new ArrayList<>(Arrays.asList(tasksSplit));
    }

    /**
     * Gets the tasks from the file.
     * @return Returns an ArrayList of Task String.
     * @throws KevinException On the detection of errors.
     */
    public ArrayList<String> getTasksFromFile() throws KevinException {
        String fileContent = storage.readFile(FILE_LOCATION);
        return makeStringToList(fileContent);
    }
}
