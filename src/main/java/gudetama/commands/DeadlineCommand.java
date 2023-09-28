package gudetama.commands;

import gudetama.storage.Storage;
import gudetama.tasks.Deadline;
import gudetama.tasks.Task;
import gudetama.tasks.TaskList;
import gudetama.ui.Ui;

/**
 * Represents a command to add a deadline to the task list
 */

public class DeadlineCommand extends Command {
    /**
     * Description of the deadline to be added
     */
    private String description;

    /**
     * Due date for the task
     */
    private String date;

    /**
     * Constructor for DeadlineCommand
     * @param description Description of the deadline to be added
     * @param date Due date for the task
     */
    public DeadlineCommand(String description, String date) {
        this.description = description;
        this.date = date;
    }

    /**
     * Executes the command that adds the deadline
     * @param tasksList Task list which contains the tasks
     * @param ui A UI instance that displays a message to indicate to the user the deadline has been added
     * @param storage Storage instance that represents the storage of the file
     */
    @Override
    public String execute(TaskList tasksList, Ui ui, Storage storage) {
        Task deadline = new Deadline(description, date);
        tasksList.addTask(deadline);
        return ui.showAddedTask(tasksList);
    }
}
