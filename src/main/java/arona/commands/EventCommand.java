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
public class EventCommand extends Command implements UndoableCommand {
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
     *
     * @return A string message indicating the message in the GUI.
     */
    @Override
    public String execute() {
        taskList.getTasks().add(eventTask);
        storage.saveTask(eventTask);
        return ui.showTaskAdded(eventTask, taskList.getTasks().size());
    }

    /**
     * Reverses the "Event" action by removing the last added task from the task list
     * and storage, and displaying a confirmation message to the user.
     *
     * @return A string message indicating the result of the undo operation.
     */
    @Override
    public String undo() {
        int lastIndex = taskList.getTasks().size() - 1;
        if (lastIndex >= 0) {
            taskList.getTasks().remove(lastIndex);
            storage.deleteTask(lastIndex);
        }
        return ui.showUndoEventCommand(eventTask);
    }

    /**
     * Retrieves the task index associated with this "Event" command.
     * As "Event" tasks don't have a specific task index, this method returns -1.
     *
     * @return The task index, which is -1 for "Event" tasks.
     */
    @Override
    public int getTaskIndex() {
        return -1;
    }
}
