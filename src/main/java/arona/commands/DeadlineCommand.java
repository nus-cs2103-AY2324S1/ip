package arona.commands;

import arona.exception.IllegalArgumentAronaException;
import arona.parser.Parser;
import arona.storage.Storage;
import arona.task.DeadlineTask;
import arona.task.TaskList;
import arona.ui.Ui;

/**
 * Represents a command to add a deadline task. When executed, this command creates a
 * new deadline task with the specified description and due date, adds it to the task list,
 * saves it to storage, and displays a confirmation message to the user interface.
 */
public class DeadlineCommand extends Command implements UndoableCommand {
    private Storage storage;
    private DeadlineTask deadlineTask;

    /**
     * Initializes a new instance of the DeadlineCommand class with the specified task list,
     * user interface, storage, and descriptions array.
     *
     * @param taskList      The task list to add the deadline task to.
     * @param ui            The user interface for displaying messages.
     * @param storage       The storage for saving the task.
     * @param descriptions  An array containing the description and due date of the deadline task.
     * @throws IllegalArgumentAronaException If the descriptions array is invalid
     *                                       or the due date is in an incorrect format.
     */
    public DeadlineCommand(TaskList taskList, Ui ui, Storage storage, String[] descriptions)
            throws IllegalArgumentAronaException {
        super(taskList, ui);
        this.storage = storage;
        this.deadlineTask = new DeadlineTask(descriptions[0], Parser.parseDate(descriptions[1]));
    }

    /**
     * Executes the "Deadline" command by creating a new deadline task, adding it to the task list,
     * saving it to storage, and displaying a confirmation message to the user interface.
     *
     * @return A string message indicating the message in the GUI.
     */
    @Override
    public String execute() {
        taskList.getTasks().add(deadlineTask);
        storage.saveTask(deadlineTask);
        return ui.showTaskAdded(deadlineTask, taskList.getTasks().size());
    }

    /**
     * Reverses the "Deadline" action by removing the last added task from the task list
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
        return ui.showUndoDeadlineCommand(deadlineTask);
    }

    /**
     * Retrieves the task index associated with this "Deadline" command.
     * As "Deadline" tasks don't have a specific task index, this method returns -1.
     *
     * @return The task index, which is -1 for "Deadline" tasks.
     */
    @Override
    public int getTaskIndex() {
        return -1;
    }
}
