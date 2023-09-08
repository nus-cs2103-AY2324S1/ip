package command;

import storage.FileHandler;
import storage.TaskList;

import taskmanager.Deadline;

import duke.Ui;

/**
 * Command to add a Deadlines task.
 */
public class AddDeadlineCommand extends Command {
    private String taskDesc;
    private String dueDateStr;

    /**
     * Constructs an `AddDeadlineCommand` object with the specified user input and due date string.
     *
     * @param taskDesc  The task description.
     * @param dueDateStr The due date and time in string.
     */
    public AddDeadlineCommand(String taskDesc, String dueDateStr) {
        this.dueDateStr = dueDateStr;
        this.taskDesc = taskDesc;
    }

    /**
     * Executes the command to add a Deadlines task to the task list, update the storage, and notify the user interface.
     *
     * @param task The task list to which the Deadlines task will be added.
     * @param ui   The user interface.
     * @param f    The file handler for storing tasks.
     *
     * @return     The string representation of the task.
     * */
    @Override
    public String execute(TaskList task, Ui ui, FileHandler f) {
        Deadline newDeadline = new Deadline(taskDesc, dueDateStr);
        task.add(newDeadline);
        FileHandler.writeTasksToFile(task);
        return "Help you added a new deadline.\n     " + newDeadline.toString()
                + "\nNow you have " + task.size() + String.format(" task(s) in the list.");
    }

    /**
     * Checks whether the command is an exit command.
     *
     * @return `false` because this command does not exit the application.
     */
    @Override
    public boolean isExit() {
        return false;
    }
}
