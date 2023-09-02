package chatbot.commands;

import chatbot.storage.Storage;
import chatbot.tasks.Deadline;
import chatbot.tasks.Task;
import chatbot.tasks.TaskList;
import chatbot.ui.Ui;

/**
 * Represents a command to add a deadline to the task list
 */
public class DeadlineCommand extends Command {
    /**
     * Description of the deadline to be added
     */
    String description;

    /**
     * Due date for the task
     */
    String date;

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
     *  Returns a boolean value to indicate whether to exit the program
     * @return A boolean value
     */
    @Override
    public boolean isExit() {
        return false;
    }

    /**
     * Executes the command that adds the deadline
     * @param tasksList Task list which contains the tasks
     * @param ui A UI instance that displays a message to indicate to the user the deadline has been added
     * @param storage Storage instance that represents the storage of the file
     */
    @Override
    public void execute(TaskList tasksList, Ui ui, Storage storage) {
        Task deadline = new Deadline(description,date);
        tasksList.addTask(deadline);
        ui.showAddedTask(tasksList);
    }
}
