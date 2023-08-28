import exceptions.DukeException;
import exceptions.InvalidDeadlineException;
import exceptions.InvalidEventException;
import exceptions.InvalidIndexException;
import exceptions.InvalidTodoException;
import exceptions.UnknownCommandException;

import extensions.TaskList;
import extensions.Task;

import java.io.File;
import java.io.FileWriter;
import java.io.FileNotFoundException;
import java.util.Scanner;

import java.util.ArrayList;
import java.time.format.DateTimeParseException;

public class Duke {

    /*
        file format:
        <tasktype>|<isMarked>|<desc>|<end>|<start>
     */

    private static TaskList taskList = new TaskList();
    private static Storage storage;

    // accept only correct input
    public static void mark2(int index) {
        Task task = Duke.taskList.markTaskAsDone(index);
        Duke.storage.saveChanges(Duke.taskList);
        Ui.mark(task);
    }

    // accept only correct input
    public static void unmark2(int index) {
        Task task = Duke.taskList.unmarkTask(index);
        Duke.storage.saveChanges(Duke.taskList);
        Ui.unmark(task);
    }

    public static void deleteTask2(int index) {
        Task task = Duke.taskList.deleteTask(index);
        Duke.storage.saveChanges(Duke.taskList);
        Ui.deleteTask(task, Duke.taskList.getSize());
    }

    public static void createTodo2(String desc) {
        Task task = Duke.taskList.addTodo(desc, 0);
        Duke.storage.saveChanges(Duke.taskList);
        Ui.addTask(task, Duke.taskList.getSize());
    }

    public static void createDeadline2(String desc, String deadline) {
        Task task = Duke.taskList.addDeadline(desc, deadline, 0);
        Duke.storage.saveChanges(Duke.taskList);
        Ui.addTask(task, Duke.taskList.getSize());
        // throws DateTimeParseException
    }

    public static void createEvent2(String desc, String start, String end) {
        Task task = Duke.taskList.addEvent(desc, start, end, 0);
        Duke.storage.saveChanges(Duke.taskList);
        Ui.addTask(task, Duke.taskList.getSize());
        // throws DateTimeParseException
    }

    public static void listTasks() {
        Ui.list(Duke.taskList);
    }

    public static void run(String filepath) {
        Duke.storage = new Storage(filepath);
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

        Duke.storage.saveChanges(Duke.taskList);
        scanner.close();
        Ui.outro();
    }

    public static void main(String[] args) {
        Duke.run("data/duke.txt");
    }

}
