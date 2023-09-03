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
    public static void parseInputString(String inputStr, TaskList tasks) {
        String[] commandArr = inputStr.split(" ");
        if (commandArr[0].equals(Commands.LS_COMMAND.getValue())) {
            RenUi.listTasks(tasks);
        } else if (commandArr[0].equals(Commands.FIND_COMMAND.getValue())) {
            RenUi.displayFoundTasks(tasks, commandArr[1]);
        } else if (commandArr[0].equals(Commands.DELETE_COMMAND.getValue())) {
            try {
                Task task = tasks.deleteTask(commandArr);
                RenUi.declareTaskDeleted(task, tasks);
            } catch (InsufficientArguments e) {
                RenUi.printException(e);
            }
        } else if (commandArr[0].equals(Commands.MARK_COMMAND.getValue())
                || commandArr[0].equals(Commands.UNMARK_COMMAND.getValue())) {
            Task task = tasks.toggleTask(commandArr);
            if (commandArr[0].equals(Commands.MARK_COMMAND.getValue())) {
                RenUi.declareTaskUpdated(task, tasks, true);
            } else {
                RenUi.declareTaskUpdated(task, tasks, false);
            }
        } else if (TASK_TYPES.contains(commandArr[0])) {
            try {
                Task task = tasks.addTask(commandArr);
                RenUi.declareTaskAdded(task, tasks);
            } catch (InsufficientArguments e) {
                RenUi.printException(e);
            }
        } else {
            RenUi.requestProperCommand();
        }
    }
}
