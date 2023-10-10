package gudetama.commands;

import gudetama.storage.Storage;
import gudetama.tasks.Event;
import gudetama.tasks.Task;
import gudetama.tasks.TaskList;
import gudetama.ui.Ui;

/**
 * Represents a command to add an event task to the task list
 */
public class EventCommand extends Command {
    /**
     * Description of the event task to be added
     */
    private String description;

    /**
     * Start date of the event
     */
    private String from;

    /**
     * End date of the event
     */
    private String to;

    /**
     * Constructor for EventCommand
     * @param description Description of the event task to be added
     * @param from Start date of the event
     * @param to End date of the event
     */
    public EventCommand(String description, String from, String to) {
        this.description = description;
        this.from = from;
        this.to = to;
    }

    /**
     * Executes the command that adds the event task
     * @param tasksList Task list which contains the tasks
     * @param ui A UI instance that displays a message to indicate to the user the event task has been added
     * @param storage Storage instance that represents the storage of the file
     */
    @Override
    public String execute(TaskList tasksList, Ui ui, Storage storage) {
        Task event = new Event(description, from, to);
        tasksList.addTask(event);
        return ui.showAddedTask(tasksList);
    }
}
