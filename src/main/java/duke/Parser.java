package duke;

import java.util.ArrayList;

import task.Deadline;
import task.Event;
import task.Task;
import task.Todo;

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
        String name = "";
        if (command.equals("deadline")) {
            String deadline = "";
            boolean completedName = false;
            for (int i = 1; i < arrStrings.length; i++) {
                if (arrStrings[i].equals("/by")) {
                    completedName = true;
                    continue;
                }
                if (completedName) {
                    deadline += arrStrings[i] + " ";
                } else {
                    name += arrStrings[i] + " ";
                }
            }
            if (!completedName) {
                throw new Exception("Invalid deadline task!");
            }
            return new Deadline(name.substring(0, name.length() - 1), deadline.substring(0, deadline.length() - 1));
        } else if (command.equals("todo")) {
            if (arrStrings.length == 1) {
                throw new Exception("OOPS!!! The description of a todo cannot be empty.");
            }
            for (int i = 1; i < arrStrings.length; i++) {
                name += arrStrings[i] + " ";
            }
            return new Todo(name.substring(0, name.length() - 1));
        } else if (command.equals("event")) {
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
            return new Event(name.substring(0, name.length() - 1), from.substring(0, from.length() - 1),
                    to.substring(0, to.length() - 1));
        } else {
            throw new Exception("OOPS!!! I'm sorry, but I don't know what that means :-(");
        }
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
    public static void parse(String input, Ui ui, TaskList taskList, Storage storage) {
        // Splits the input based on whitespaces.
        String command = input.split("\\s+")[0];
        int choice = -1;
        switch (command) {
        case "list":
            ui.listTask(taskList);
            break;
        case "mark":
            choice = Integer.parseInt(input.split("\\s+")[1]);
            taskList.mark(choice);
            ui.displayMarkTask(taskList, choice);
            break;
        case "unmark":
            choice = Integer.parseInt(input.split("\\s+")[1]);
            taskList.unmark(choice);
            ui.displayUnmarkTask(taskList, choice);
            break;
        case "delete":
            choice = Integer.parseInt(input.split("\\s+")[1]);
            Task removedTask = taskList.delete(choice);
            ui.displayDeleteTask(removedTask, taskList);
            break;
        case "find":
            String word = input.split("\\s+")[1];
            ArrayList<Task> matchingList = taskList.find(word);
            ui.displayMatchingTask(matchingList);
            break;
        default:
            Task task = null;
            try {
                task = createTask(input);
            } catch (Exception e) {
                ui.showExceptionError(e);
            }
            if (task != null) {
                taskList.add(task);
                ui.displayAddTask(task, taskList);
            }
        }
        storage.updateTasks(taskList);
    }

}
