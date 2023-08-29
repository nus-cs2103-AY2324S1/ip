package duke;

import java.time.format.DateTimeParseException;
import java.util.Scanner;

import exceptions.DukeException;
import tasks.Task;
import tasks.TaskList;

/**
 * Duke is a chatbot that allows users to add different types of tasks and manage them.
 */
public class Duke {

    private static TaskList taskList = new TaskList();
    private static Storage storage;

    /**
     * Marks the indicated task as done.
     *
     * @param index Index of task to be marked as done.
     * @throws DukeException If index provided is out of range.
     */
    public static void mark(int index)
            throws DukeException {
        Task task = Duke.taskList.markTaskAsDone(index);
        storage.saveChanges(Duke.taskList);
        Ui.mark(task);
    }

    /**
     * Marks the indicated task as not done.
     *
     * @param index Index of task to be marked as done.
     * @throws DukeException If index provided is out of range.
     */
    public static void unmark(int index)
            throws DukeException {
        Task task = Duke.taskList.unmarkTask(index);
        storage.saveChanges(Duke.taskList);
        Ui.unmark(task);
    }

    /**
     * Deletes the indicated task.
     *
     * @param index Index of task to be deleted.
     * @throws DukeException If index provided is out of range.
     */
    public static void deleteTask(int index)
            throws DukeException {
        Task task = Duke.taskList.deleteTask(index);
        storage.saveChanges(Duke.taskList);
        Ui.deleteTask(task, Duke.taskList.getSize());
    }

    /**
     * Creates a todo task.
     *
     * @param desc Description of task.
     */
    public static void createTodo(String desc) {
        Task task = Duke.taskList.addTodo(desc, 0);
        storage.saveChanges(Duke.taskList);
        Ui.addTask(task, Duke.taskList.getSize());
    }

    /**
     * Creates a Deadline task with a deadline date and time.
     *
     * @param desc Description of task.
     * @param deadline Deadline of task.
     * @throws DateTimeParseException If deadline provided is not of format "yyyy-MM-dd HHmm".
     */
    public static void createDeadline(String desc, String deadline)
            throws DateTimeParseException {
        Task task = Duke.taskList.addDeadline(desc, deadline, 0);
        storage.saveChanges(Duke.taskList);
        Ui.addTask(task, Duke.taskList.getSize());
    }

    /**
     * Creates an Event task with a start and end date and time.
     *
     * @param desc Description of task.
     * @param start Start of task.
     * @param end End of task.
     * @throws DateTimeParseException if start, end provided are not of format "yyyy-MM-dd HHmm".
     */
    public static void createEvent(String desc, String start, String end)
            throws DateTimeParseException {
        Task task = Duke.taskList.addEvent(desc, start, end, 0);
        storage.saveChanges(Duke.taskList);
        Ui.addTask(task, Duke.taskList.getSize());
    }

    /**
     * Lists out all the current tasks.
     */
    public static void listTasks() {
        Ui.list(Duke.taskList);
    }

    /**
     * Initialises or retrieves a storage file and runs the chatbot.
     *
     * @param filename Name of file for storing task data.
     */
    public static void run(String filename) {
        Storage storage = new Storage(filename);
        Duke.storage = storage;
        Duke.taskList = storage.retrieveSavedData();

        Ui.intro();
        Scanner scanner = new Scanner(System.in);
        String userInput = scanner.nextLine();
        String bye = "bye";

        while (!userInput.toLowerCase().equals(bye)) {
            try {
                Parser.processInput(userInput, Duke.taskList.getSize());
            } catch (RuntimeException e) {
                Ui.printError(e.getMessage());
            } finally {
                userInput = scanner.nextLine();
            }
        }

        storage.saveChanges(Duke.taskList);
        scanner.close();
        Ui.outro();
    }

    /**
     * Launches the chatbot.
     *
     * @param args Command line arguments.
     */
    public static void main(String[] args) {
        Duke.run("duke.txt");
    }

}
