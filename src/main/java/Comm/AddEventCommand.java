package Comm;
import Ui.Ui;
import Storage.TaskList;
import Storage.FileHandler;
import TaskManager.Events;

/**
 * Command to add an Events task.
 */
public class AddEventCommand extends Command{
    private String taskDesc;
    private String to;
    private String from;

    /**
     * Constructs an `AddEventCommand` object with the specified user input and due date string.
     *
     * @param taskDesc The task description.
     * @param from      The start date and time in string.
     * @param to        The end date and time in string.
     */
    public AddEventCommand(String taskDesc, String from, String to) {

        this.taskDesc = taskDesc;
        this.from = from;
        this.to = to;

    }

    /**
     * Executes the command to add an Events task to the task list, update the storage, and notify the user interface.
     *
     * @param task The task list to which the Events task will be added.
     * @param ui   The user interface.
     * @param f    The file handler for storing tasks.
     */
    @Override
    public void execute(TaskList task, Ui ui, FileHandler f) {

        Events newEvent = new Events(taskDesc, from, to);
        if (newEvent.isValid()) {
            task.add(newEvent);
            FileHandler.writeTasksToFile(task);
            ui.addedEvent(newEvent);
        }

    }

    /**
     * Check whether the command is an exit command.
     *
     * @return `false` because this command does not exit the application.
     */
    @Override
    public boolean isExit() {

        return false;

    }
}