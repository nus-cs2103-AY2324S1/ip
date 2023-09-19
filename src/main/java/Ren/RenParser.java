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
     * Handles the input string and executes the corresponding LIST command.
     * Returns the list of tasks.
     *
     * @param tasks
     * @return the list of tasks
     */
    private static String handleList(TaskList tasks) {
        return tasks.toString();
    }

    /**
     * Handles the input string and returns filtered list of tasks.
     *
     * @param commandArr
     * @param tasks
     * @return filtered list of tasks
     */
    private static String handleFind(String[] commandArr, TaskList tasks) {
        return tasks.filterToListOfMatchingTasks(commandArr[1]).toString();
    }

    /**
     * Handles the input string and returns the corresponding command.
     *
     * @param commandArr the array of the command split by whitespace
     * @param tasks      the list of tasks
     * @return the deleted task
     */
    private static String handleDelete(String[] commandArr, TaskList tasks) {
        try {
            Task task = tasks.deleteTask(commandArr);
            return task + " deleted";
        } catch (InsufficientArgumentsException e) {
            RenUi.printException(e);
            return e.getMessage();
        }
    }

    /**
     * Handles the input string and returns the corresponding command.
     *
     * @param commandArr the array of the command split by whitespace
     * @param tasks      the list of tasks
     * @param isMark     whether the command is a mark command
     * @return the marked task
     */
    private static String handleMark(String[] commandArr, TaskList tasks, boolean isMark) {
        Task task = tasks.toggleTask(commandArr);
        if (isMark) {
            return task + " marked as done";
        } else {
            return task + " marked as undone";
        }
    }

    /**
     * Handles the input string and returns the corresponding command.
     *
     * @param commandArr the array of the command split by whitespace
     * @param tasks      the list of tasks
     * @return the added task
     */
    private static String handleAddTask(String[] commandArr, TaskList tasks) {
        try {
            Task task = tasks.addTask(commandArr);
            return task + " added";
        } catch (InsufficientArgumentsException e) {
            RenUi.printException(e);
            return e.getMessage();
        }
    }

    /**
     * Parses the input string and executes the corresponding command.
     *
     * @param inputStr the input string
     * @param tasks    the list of tasks
     */
    public static String parseInputString(String inputStr, TaskList tasks) {
        boolean isExit = inputStr.equals(Commands.EXIT_COMMAND.getValue());
        if (isExit) {
            return "Bye. Hope to see you again soon!";
        }

        String[] commandArr = inputStr.split(" ");
        boolean isList = commandArr[0].equals(Commands.LS_COMMAND.getValue());
        boolean isFind = commandArr[0].equals(Commands.FIND_COMMAND.getValue());
        boolean isDelete = commandArr[0].equals(Commands.DELETE_COMMAND.getValue());
        boolean isMark = commandArr[0].equals(Commands.MARK_COMMAND.getValue());
        boolean isUnmark = commandArr[0].equals(Commands.UNMARK_COMMAND.getValue());
        boolean isAddTask = TASK_TYPES.contains(commandArr[0]);

        if (isList) {
            return handleList(tasks);
        } else if (isFind) {
            return handleFind(commandArr, tasks);
        } else if (isDelete) {
            return handleDelete(commandArr, tasks);
        } else if (isMark || isUnmark) {
            return handleMark(commandArr, tasks, isMark);
        } else if (isAddTask) {
            return handleAddTask(commandArr, tasks);
        } else {
            return "Please enter a proper command";
        }
    }

}
