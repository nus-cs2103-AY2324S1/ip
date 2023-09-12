package gbot;

import exceptions.*;

import java.util.ArrayList;
import tasks.Deadline;
import tasks.Event;
import tasks.Task;
import tasks.Todo;

/**
 * The chatbot parser for the command inputs of users.
 *
 * @author Gallen Ong
 */
public class Parser {
    /**
     * Returns corresponding methods after parsing user inputs.
     *
     * @param message The user input.
     * @param tasks The TaskList object that stores tasks.
     * @throws GBotException If user input is invalid.
     */
    public static String parse(String message, TaskList tasks) throws GBotException {
        assert !message.isBlank() : "Please enter a valid command.";
        if (message.strip().equals("list")) {
            return tasks.listTasks();
        }

        String prefix = message.split(" ")[0];
        String str = message.substring(prefix.toString().length() + 1);
        String result;

        switch (prefix) {
        case "mark":
            if (str.isBlank()) {
                return "Please enter a task number.";
            }
            try {
                result = tasks.markTask(Integer.parseInt(str.split(" ")[0]));
            } catch (NumberFormatException e) {
                return "Please enter a task number.";
            }
            break;
        case "unmark":
            if (str.isBlank()) {
                return "Please enter a task number.";
            }
            try {
                result = tasks.unmarkTask(Integer.parseInt(str.split(" ")[0]));
            } catch (NumberFormatException e) {
                return "Please enter a task number.";
            }
            break;
        case "todo":
            if (str.isBlank()) {
                throw new TodoException();
            }
            result = tasks.addTodo(str);
            break;
        case "deadline":
            if (str.isBlank()) {
                throw new DeadlineException();
            }
            result = tasks.addDeadline(str);
            break;
        case "event":
            if (str.isBlank()) {
                throw new EventException();
            }
            result = tasks.addEvent(str);
            break;
        case "delete":
            if (str.isBlank()) {
                return "Please enter a task number.";
            }
            try {
                result = tasks.deleteTask(Integer.parseInt(str.split(" ")[0]));
            } catch (NumberFormatException e) {
                return "Please enter a task number.";
            }
            break;
        case "find":
            if (str.isBlank()) {
                return "Please enter a keyword.";
            }
            result = tasks.find(str);
            break;
        default:
            throw new GBotException();
        }
        return result;
    }

    /**
     * Loads tasks line by line from file provided and adds to task list provided.
     *
     * @param taskInFile The current task line in the file.
     * @param tasks The task list provided and to be updated.
     */
    public static void loadTaskFromFile(String taskInFile, ArrayList<Task> tasks) {
        String[] taskArr = taskInFile.split(" \\| ");
        switch (taskArr[0]) {
        case "T":
            tasks.add(new Todo(taskArr[1], taskArr[2]));
            break;
        case "D":
            tasks.add(new Deadline(taskArr[1], taskArr[2], taskArr[3]));
            break;
        case "E":
            tasks.add(new Event(taskArr[1], taskArr[2], taskArr[3], taskArr[4]));
            break;
        default:
            break;
        }
    }
}
