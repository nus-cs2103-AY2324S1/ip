package duke.command;

import duke.exception.ChatException;
import duke.storage.Storage;
import duke.task.Event;
import duke.task.Task;
import duke.task.TaskList;
import duke.ui.Ui;

/**
 * Prompts the program to create a new task with starting and ending time.
 */
public class EventCommand extends Command {
    private String description;
    private String from;
    private String to;

    /**
     * Constructor for the class EventCommand.
     * @param description Description of the task.
     * @param from Starting time of the task.
     * @param to Ending time of the task.
     */
    public EventCommand(String description, String from, String to) {
        this.description = description;
        this.from = from;
        this.to = to;
    }
    /**
     * Creates a new task with a starting and ending time and stores it in tasks,
     * shows appropriate response to user and saves task to storage.
     * @param tasks List of task stored by the program.
     * @param ui Responses to be shown to user.
     * @param storage Saves the list of task to be accessed in the future.
     */
    public String execute(TaskList tasks, Ui ui, Storage storage) {
        try {
            Task event = new Event(this.description, this.from, this.to);
            tasks.addTask(event);
            storage.saveList(tasks);
            return ui.addTaskResponse(event, tasks);
        } catch (ChatException e) {
            return ui.showLoadingError(e);
        }
    };
}
