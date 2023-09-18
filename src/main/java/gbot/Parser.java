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
        String remainingInput = message.substring(prefix.length() + 1);
        switch (prefix) {
        case "mark":
            return tasks.markTask(remainingInput);
        case "unmark":
            return tasks.unmarkTask(remainingInput);
        case "todo":
            return tasks.addTodo(remainingInput);
        case "deadline":
            return tasks.addDeadline(remainingInput);
        case "event":
            return tasks.addEvent(remainingInput);
        case "delete":
            return tasks.deleteTask(remainingInput);
        case "find":
            return tasks.find(remainingInput);
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
