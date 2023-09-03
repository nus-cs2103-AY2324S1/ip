package shiba.commands;

import shiba.exceptions.EmptyTasksException;
import shiba.exceptions.InvalidCommandException;
import shiba.exceptions.ShibaException;
import shiba.tasks.PersistentTaskList;
import shiba.tasks.ShibaTask;
import shiba.ui.Replier;

/**
 * Represents a command that the user can give to Shiba.
 */
public abstract class ShibaCommand {
    /**
     * The types of commands that Shiba can execute.
     */
    public enum CommandType {
        BYE,
        LIST,
        MARK,
        UNMARK,
        TODO,
        DEADLINE,
        EVENT,
        DELETE,
        FIND
    }

    protected final PersistentTaskList tasks;

    protected ShibaCommand(PersistentTaskList tasks) {
        this.tasks = tasks;
    }

    /**
     * Executes the command.
     *
     * @throws ShibaException If there is an error executing the command.
     */
    public abstract void execute() throws ShibaException;

    /**
     * Checks if the task number is valid in the command. It should be present
     * as the 2nd parameter.
     *
     * @param cmd The command parameters, split by spaces.
     * @return The task number if valid.
     * @throws ShibaException If the task number is missing, invalid, or there are no tasks in the list.
     */
    protected int checkTaskNumber(String[] cmd) throws ShibaException {
        if (cmd.length < 2) {
            throw new InvalidCommandException("Please specify a task number!");
        }

        try {
            int taskNumber = Integer.parseInt(cmd[1]);
            if (taskNumber < 1 || taskNumber > tasks.size()) {
                if (taskNumber > tasks.size() && tasks.size() == 0) {
                    throw new EmptyTasksException();
                }
                throw new InvalidCommandException("Please specify a valid task number!");
            }
            return taskNumber;
        } catch (NumberFormatException e) {
            throw new InvalidCommandException("Invalid task number! Please enter a positive integer.");
        }
    }

    /**
     * Adds a task to the list.
     *
     * @param task The task to be added.
     * @throws ShibaException If there is an error saving the task after adding it.
     */
    protected void addTask(ShibaTask task) throws ShibaException {
        tasks.addTask(task);

        Replier.printWithLevel2Indent("Woof! I've added this task:");
        Replier.printWithLevel3Indent(task.toString());
        String taskWord = tasks.size() == 1 ? " task" : " tasks";
        Replier.printWithLevel2Indent("You now have " + tasks.size() + taskWord
                + " in the list. Now gimme some treats.");
        Replier.reply();
        tasks.save();
    }
}
