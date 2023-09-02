package gbot;

import exceptions.DeadlineException;
import exceptions.EventException;
import exceptions.GBotException;
import exceptions.TodoException;
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
    enum Keyword {
        MARK,
        UNMARK,
        TODO,
        DEADLINE,
        EVENT,
        DELETE
    }

    /**
     * Returns corresponding methods after parsing user inputs.
     *
     * @param message The user input.
     * @param tasks The TaskList object that stores tasks.
     * @throws GBotException If user input is invalid.
     */
    public static void parse(String message, TaskList tasks) throws GBotException {
        if (message.isBlank()) {
            Ui.showEmptyCommandError();
            return;
        } else if (message.equals("list")) {
            tasks.listTasks();
        }

        Keyword prefix = Keyword.valueOf(message.split(" ")[0].toUpperCase());
        String str = message.substring(prefix.toString().length() + 1);

        switch (prefix) {
        case MARK:
            if (str.isBlank()) {
                Ui.showTaskNumberError();
            }
            try {
                tasks.markTask(Integer.parseInt(str.split(" ")[1]));
            } catch (NumberFormatException e) {
                Ui.showTaskNumberError();
            }
            break;
        case UNMARK:
            if (str.isBlank()) {
                Ui.showTaskNumberError();
            }
            try {
                tasks.unmarkTask(Integer.parseInt(str.split(" ")[1]));
            } catch (NumberFormatException e) {
                Ui.showTaskNumberError();
            }
            break;
        case TODO:
            if (str.isBlank()) {
                throw new TodoException();
            }
            tasks.addTodo(str);
            break;
        case DEADLINE:
            if (str.isBlank()) {
                throw new DeadlineException();
            }
            tasks.addDeadline(str);
            break;
        case EVENT:
            if (str.isBlank()) {
                throw new EventException();
            }
            tasks.addEvent(str);
            break;
        case DELETE:
            if (str.isBlank()) {
                Ui.showTaskNumberError();
            }
            try {
                tasks.deleteTask(Integer.parseInt(str.split(" ")[1]));
            } catch (NumberFormatException e) {
                Ui.showTaskNumberError();
            }
            break;
        default:
            throw new GBotException();
        }
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
