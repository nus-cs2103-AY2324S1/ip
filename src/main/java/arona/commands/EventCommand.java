package arona.commands;

import arona.storage.Storage;
import arona.task.EventTask;
import arona.task.TaskList;
import arona.ui.Ui;

/**
 * Represents a command to add an event task. When executed, this command creates
 * a new event task and adds it to the task list. It also saves the task to storage
 * and displays a confirmation message to the user interface.
 */
public class EventCommand extends Command {
    private Storage storage;
    private EventTask eventTask;


    /**
     * Initializes a new instance of the EventCommand class with the specified
     * task list, user interface, storage, and event task descriptions.
     *
     * @param taskList      The task list to which the event task will be added.
     * @param ui            The user interface for displaying messages.
     * @param storage       The storage for saving the event task.
     * @param descriptions  An array containing the event task descriptions, including
     *                      the description, starting date, and ending date.
     */
    public EventCommand(TaskList taskList, Ui ui, Storage storage, String[] descriptions) {
        super(taskList, ui);
        this.storage = storage;
        this.eventTask = new EventTask(descriptions[0], descriptions[1], descriptions[2]);
    }

    /**
     * Executes the "Event" command by adding the event task to the task list,
     * saving it to storage, and displaying a confirmation message to the user interface.
     */
    @Override
    public void execute() {
        taskList.getTasks().add(eventTask);
        storage.saveTask(eventTask);
        ui.showTaskAdded(eventTask, taskList.getTasks().size());
    }
}
