package ren;

import ren.task.Task;
import ren.task.TaskList;


/**
 * Represents the parser that parses the input string and executes the corresponding command.
 */
public class RenParser {
    private static final java.util.Set<String> TASK_TYPES = java.util.Set.of(
            "todo", "deadline", "event"
    );

    /**
     * Parses the input string and executes the corresponding command.
     *
     * @param inputStr the input string
     * @param tasks    the list of tasks
     */
    public static String parseInputString(String inputStr, TaskList tasks) {
        if (inputStr.equals(Commands.EXIT_COMMAND.getValue())) {
            return "Bye. Hope to see you again soon!";
        }
        String[] commandArr = inputStr.split(" ");
        if (commandArr[0].equals(Commands.LS_COMMAND.getValue())) {
            return tasks.toString();
        } else if (commandArr[0].equals(Commands.FIND_COMMAND.getValue())) {
            return tasks.filterToListOfMatchingTasks(commandArr[1]).toString();
        } else if (commandArr[0].equals(Commands.DELETE_COMMAND.getValue())) {
            try {
                Task task = tasks.deleteTask(commandArr);
                return task + " deleted";
            } catch (InsufficientArgumentsException e) {
                RenUi.printException(e);
            }
        } else if (commandArr[0].equals(Commands.MARK_COMMAND.getValue())
                || commandArr[0].equals(Commands.UNMARK_COMMAND.getValue())) {
            Task task = tasks.toggleTask(commandArr);
            if (commandArr[0].equals(Commands.MARK_COMMAND.getValue())) {
                return task + " marked as done";
            } else {
                return task + " marked as undone";
            }
        } else if (TASK_TYPES.contains(commandArr[0])) {
            try {
                Task task = tasks.addTask(commandArr);
                return task + " added";
            } catch (InsufficientArgumentsException e) {
                RenUi.printException(e);
            }
        } else {
            return "Please enter a proper command";
        }
        return "";
    }

}
