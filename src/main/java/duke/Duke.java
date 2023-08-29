package duke;

import java.time.format.DateTimeParseException;
import java.util.Scanner;

import exceptions.DukeException;
import tasks.Task;
import tasks.TaskList;

public class Duke {

    private static TaskList taskList = new TaskList();
    private static Storage storage;

    public static void mark(int index)
            throws DukeException {
        Task task = Duke.taskList.markTaskAsDone(index);
        storage.saveChanges(Duke.taskList);
        Ui.mark(task);
    }

    public static void unmark(int index)
            throws DukeException {
        Task task = Duke.taskList.unmarkTask(index);
        storage.saveChanges(Duke.taskList);
        Ui.unmark(task);
    }

    public static void deleteTask(int index)
            throws DukeException {
        Task task = Duke.taskList.deleteTask(index);
        storage.saveChanges(Duke.taskList);
        Ui.deleteTask(task, Duke.taskList.getSize());
    }

    public static void createTodo(String desc) {
        Task task = Duke.taskList.addTodo(desc, 0);
        storage.saveChanges(Duke.taskList);
        Ui.addTask(task, Duke.taskList.getSize());
    }

    public static void createDeadline(String desc, String deadline)
            throws DateTimeParseException {
        Task task = Duke.taskList.addDeadline(desc, deadline, 0);
        storage.saveChanges(Duke.taskList);
        Ui.addTask(task, Duke.taskList.getSize());
    }

    public static void createEvent(String desc, String start, String end)
            throws DateTimeParseException {
        Task task = Duke.taskList.addEvent(desc, start, end, 0);
        storage.saveChanges(Duke.taskList);
        Ui.addTask(task, Duke.taskList.getSize());
    }

    public static void listTasks() {
        Ui.list(Duke.taskList);
    }

    /**
     * Prints list of filtered tasks.
     *
     * @param keyword String of list of filtered tasks.
     */
    public static void listFilteredTasks(String keyword) {
        Ui.printMessage(Duke.taskList.getMatchingTasks(keyword));
    }

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
                Ui.printMessage(e.getMessage());
            } finally {
                userInput = scanner.nextLine();
            }
        }

        storage.saveChanges(Duke.taskList);
        scanner.close();
        Ui.outro();
    }

    public static void main(String[] args) {
        Duke.run("duke.txt");
    }

}
