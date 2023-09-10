package duke;

import java.util.ArrayList;

import duke.task.Deadline;
import duke.task.Event;
import duke.task.Task;
import duke.task.Todo;

/**
 * Encapsulates the logic of analysing the user's String input.
 * Decides on what should be done next based on it
 *
 * @author Donovan Chan Jia Jun
 */
public class Parser {

    /**
     * Checks if input string is parsable or not.
     *
     * @param input The input string to check
     * @return {@code true} if the input string is not equal to "bye"
     */
    public static boolean parsable(String input) {
        return !input.equals("bye");
    }

    /**
     * Creates the tasks based on String input.
     *
     * @param input String input by user
     * @return A task object of the appropriate subclass (Deadlines, Events, Todos)
     * @throws Exception If the input string is not valid
     */
    public static Task createTask(String input) throws Exception {
        // Splits based on white spaces, identifies based on the relevant /...
        String[] arrStrings = input.split("\\s+");
        String command = arrStrings[0];
        if (command.equals("deadline")) {
            return createDeadline(arrStrings);
        } else if (command.equals("todo")) {
            return createTodo(arrStrings);
        } else if (command.equals("event")) {
            return createEvent(arrStrings);
        } else {
            throw new Exception("OOPS!!! I'm sorry, but I don't know what that means :-(");
        }
    }

    private static Event createEvent(String[] arrStrings) {
        String name = "";
        String from = "";
        String to = "";
        boolean completedName = false;
        boolean completedFrom = false;
        for (int i = 1; i < arrStrings.length; i++) {
            if (arrStrings[i].equals("/from")) {
                completedName = true;
            } else if (arrStrings[i].equals("/to")) {
                completedFrom = true;
            } else if (!completedFrom && completedName) {
                from += arrStrings[i] + " ";
            } else if (completedFrom && completedName) {
                to += arrStrings[i] + " ";
            } else {
                name += arrStrings[i] + " ";
            }
        }
        name = name.substring(0, name.length() - 1);
        from = from.substring(0, from.length() - 1);
        to = to.substring(0, to.length() - 1);
        return new Event(name, from, to);
    }

    private static Todo createTodo(String[] arrStrings) throws Exception {
        String name = "";
        if (arrStrings.length == 1) {
            throw new Exception("OOPS!!! The description of a todo cannot be empty.");
        }
        for (int i = 1; i < arrStrings.length; i++) {
            name += arrStrings[i] + " ";
        }
        name = name.substring(0, name.length() - 1);
        return new Todo(name);
    }

    /**
     * Create a new Deadline object based on the String input which has been split into parts.
     *
     * @param arrStrings Array of String which has been split from the user input
     * @return new Deadline Object
     * @throws Exception When the user did not input any name for the task
     */
    private static Deadline createDeadline(String[] arrStrings) throws Exception {
        String name = "";
        String deadline = "";
        boolean completedName = false;
        for (int i = 1; i < arrStrings.length; i++) {
            if (arrStrings[i].equals("/by")) {
                completedName = true;
                continue;
            }
            if (completedName) {
                deadline += arrStrings[i] + " ";
                continue;
            }
            name += arrStrings[i] + " ";
        }
        if (!completedName) {
            throw new Exception("Invalid deadline task!");
        }
        name = name.substring(0, name.length() - 1);
        deadline = deadline.substring(0, deadline.length() - 1);
        return new Deadline(name, deadline);
    }

    /**
     * Parse the input string by splitting based on whtiespaces.
     * Sorts the string based on its command
     *
     * @param input The input string representing the task given by user
     * @param ui The user interface responsible for sending outputs to the screen
     * @param taskList ArrayList that stores the tasks loaded from memory
     * @param storage Storage that can be written to or read from
     */
    public static String parse(String input, Ui ui, TaskList taskList, Storage storage) {
        // Splits the input based on whitespaces.
        String command = input.split("\\s+")[0];
        String responseMessage = getResponseMessage(input, ui, taskList, command);
        storage.updateTasks(taskList);
        return responseMessage;
    }

    private static String getResponseMessage(String input, Ui ui, TaskList taskList, String command) {
        String responseMessage = "";
        switch (command) {
        case "list":
            responseMessage = ui.listTask(taskList);
            break;
        case "mark":
            responseMessage = markTask(input, ui, taskList);
            break;
        case "unmark":
            responseMessage = unmarkTask(input, ui, taskList);
            break;
        case "delete":
            responseMessage = deleteTask(input, ui, taskList);
            break;
        case "find":
            responseMessage = findTask(input, ui, taskList);
            break;
        default:
            try {
                Task task = createTask(input);
                if (task != null) {
                    taskList.add(task);
                    responseMessage = ui.displayAddTask(task, taskList);
                }
            } catch (Exception e) {
                responseMessage = ui.showExceptionError(e);
            }
        }
        return responseMessage;
    }

    private static String findTask(String input, Ui ui, TaskList taskList) {
        String responseMessage;
        String word = input.split("\\s+")[1];
        ArrayList<Task> matchingList = taskList.find(word);
        responseMessage = ui.displayMatchingTask(matchingList);
        return responseMessage;
    }

    private static String markTask(String input, Ui ui, TaskList taskList) {
        String responseMessage;
        int choice;
        choice = getChoice(input);
        taskList.mark(choice);
        responseMessage = ui.displayMarkTask(taskList, choice);
        return responseMessage;
    }

    private static String unmarkTask(String input, Ui ui, TaskList taskList) {
        String responseMessage;
        int choice;
        choice = getChoice(input);
        taskList.unmark(choice);
        responseMessage = ui.displayUnmarkTask(taskList, choice);
        return responseMessage;
    }

    private static String deleteTask(String input, Ui ui, TaskList taskList) {
        String responseMessage;
        int choice;
        choice = getChoice(input);
        Task removedTask = taskList.delete(choice);
        responseMessage = ui.displayDeleteTask(removedTask, taskList);
        return responseMessage;
    }

    private static int getChoice(String input) {
        int choice;
        choice = Integer.parseInt(input.split("\\s+")[1]);
        return choice;
    }
}
