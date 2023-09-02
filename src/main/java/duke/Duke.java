package duke;

import java.time.format.DateTimeParseException;
import java.util.Scanner;

import exceptions.DukeException;
import tasks.Task;
import tasks.TaskList;

/**
 * Duke is a chatbot that allows users to manage tasks.
 */
public class Duke {

    private static TaskList taskList = new TaskList();
    private static Storage storage;

    /**
     * Marks task as done.
     *
     * @param index Index of task.
     * @throws DukeException If index is out of range.
     */
    public static void mark(int index)
            throws DukeException {
        Task task = Duke.taskList.markTaskAsDone(index);
        storage.saveChanges(Duke.taskList);
        Ui.printMarkingOfTask(task);
    }

    /**
     * Marks task as not done.
     *
     * @param index Index of task.
     * @throws DukeException If index is out of range.
     */
    public static void unmark(int index)
            throws DukeException {
        Task task = Duke.taskList.unmarkTask(index);
        storage.saveChanges(Duke.taskList);
        Ui.printUnmarkingOfTask(task);
    }

    /**
     * Deletes task.
     *
     * @param index Index of task.
     * @throws DukeException If index is out of range.
     */
    public static void deleteTask(int index)
            throws DukeException {
        Task task = Duke.taskList.deleteTask(index);
        storage.saveChanges(Duke.taskList);
        Ui.printDeletingOfTask(task, Duke.taskList.getSize());
    }

    /**
     * Creates todo task.
     *
     * @param desc Description of task.
     */
    public static void createTodo(String desc) {
        Task task = Duke.taskList.addTodo(desc, 0);
        storage.saveChanges(Duke.taskList);
        Ui.printAddingOfTask(task, Duke.taskList.getSize());
    }

    /**
     * Creates deadline task.
     *
     * @param desc Description of task.
     * @param deadline Deadline date/time.
     * @throws DateTimeParseException If deadline doesn't match format "yyyy-MM-dd HHmm".
     */
    public static void createDeadline(String desc, String deadline)
            throws DateTimeParseException {
        Task task = Duke.taskList.addDeadline(desc, deadline, 0);
        storage.saveChanges(Duke.taskList);
        Ui.printAddingOfTask(task, Duke.taskList.getSize());
    }

    /**
     * Creates event task.
     *
     * @param desc Description of task.
     * @param start Start date/time.
     * @param end End date/time.
     * @throws DateTimeParseException If start/end don't match format "yyyy-MM-dd HHmm".
     */
    public static void createEvent(String desc, String start, String end)
            throws DateTimeParseException {
        Task task = Duke.taskList.addEvent(desc, start, end, 0);
        storage.saveChanges(Duke.taskList);
        Ui.printAddingOfTask(task, Duke.taskList.getSize());
    }

    /**
     * Prints out current tasks.
     */
    public static void listTasks() {
        Ui.printTaskList(Duke.taskList);
    }

    /**
     * Prints list of filtered tasks.
     *
     * @param keyword String of list of filtered tasks.
     */
    public static void listFilteredTasks(String keyword) {
        Ui.printMessage(Duke.taskList.getMatchingTasks(keyword));
    }

    /**
     * Runs the chatbot.
     *
     * @param filename Name of file to store task data.
     */
    public static void run(String filename) {
        Storage storage = new Storage(filename);
        Duke.storage = storage;
        Duke.taskList = storage.retrieveSavedData();

        Ui.printIntro();
        Scanner scanner = new Scanner(System.in);
        String userInput = scanner.nextLine();
        String bye = "bye";

        while (!userInput.toLowerCase().equals(bye)) {
            try {
                Parser.parseUserInput(userInput, Duke.taskList.getSize());
            } catch (RuntimeException e) {
                Ui.printMessage(e.getMessage());
            } finally {
                userInput = scanner.nextLine();
            }
        }

        storage.saveChanges(Duke.taskList);
        scanner.close();
        Ui.printOutro();
    }

    /**
     * Entrypoint into chatbot application.
     *
     * @param args Command line arguments.
     */
    public static void main(String[] args) {
        Duke.run("duke.txt");
    }

}
