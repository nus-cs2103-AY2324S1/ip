import java.time.format.DateTimeParseException;
import java.util.Scanner;

import exceptions.DukeException;
import extensions.Task;
import extensions.TaskList;

public class Duke {

    private static TaskList taskList = new TaskList();

    public static void mark(int index)
            throws DukeException {
        Task task = Duke.taskList.markTaskAsDone(index);
        Storage.saveChanges(Duke.taskList);
        Ui.mark(task);
    }

    public static void unmark(int index)
            throws DukeException {
        Task task = Duke.taskList.unmarkTask(index);
        Storage.saveChanges(Duke.taskList);
        Ui.unmark(task);
    }

    public static void deleteTask(int index)
            throws DukeException {
        Task task = Duke.taskList.deleteTask(index);
        Storage.saveChanges(Duke.taskList);
        Ui.deleteTask(task, Duke.taskList.getSize());
    }

    public static void createTodo(String desc) {
        Task task = Duke.taskList.addTodo(desc, 0);
        Storage.saveChanges(Duke.taskList);
        Ui.addTask(task, Duke.taskList.getSize());
    }

    public static void createDeadline(String desc, String deadline)
            throws DateTimeParseException {
        Task task = Duke.taskList.addDeadline(desc, deadline, 0);
        Storage.saveChanges(Duke.taskList);
        Ui.addTask(task, Duke.taskList.getSize());
    }

    public static void createEvent(String desc, String start, String end)
            throws DateTimeParseException {
        Task task = Duke.taskList.addEvent(desc, start, end, 0);
        Storage.saveChanges(Duke.taskList);
        Ui.addTask(task, Duke.taskList.getSize());
    }

    public static void listTasks() {
        Ui.list(Duke.taskList);
    }

    public static void run() {
        Duke.taskList = Storage.retrieveSavedData();

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

        Storage.saveChanges(Duke.taskList);
        scanner.close();
        Ui.outro();
    }

    public static void main(String[] args) {
        Duke.run();
    }

}
