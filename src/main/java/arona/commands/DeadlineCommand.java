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
public class DeadlineCommand extends Command {
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
     */
    @Override
    public void execute() {
        taskList.getTasks().add(deadlineTask);
        storage.saveTask(deadlineTask);
        ui.showTaskAdded(deadlineTask, taskList.getTasks().size());
    }
}
