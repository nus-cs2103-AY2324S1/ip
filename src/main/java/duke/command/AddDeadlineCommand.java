package duke.command;

import duke.tasks.Deadline;
import duke.tasks.TaskList;
import duke.util.Storage;
import duke.util.Ui;


/**
 * Represents a command to add a deadline task to the task list.
 * <p>
 * The {@code AddDeadlineCommand} class encapsulates the details of the deadline,
 * including its description and due date. When executed, the deadline is added
 * to the task list and the list is saved using the provided storage.
 * </p>
 */
public class AddDeadlineCommand extends Command {

    /**
     * Due date of the deadline task.
     */
    private final String by;

    /**
     * Description of the deadline task.
     */
    private final String description;

    /**
     * Constructs a new {@code AddDeadlineCommand} with the provided description
     * and due date.
     *
     * @param description description of the deadline task.
     * @param by due date of the deadline task.
     */
    public AddDeadlineCommand(String description, String by) {
        this.description = description;
        this.by = by;
    }

    /**
     * Executes the command by adding a new deadline to the provided task list,
     * and saving the updated list using the provided storage.
     *
     * @param tasks list of tasks.
     * @param ui user interface.
     * @param storage storage system.
     */
    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) {
        tasks.addTask(new Deadline(description, by));
        storage.save(tasks.getTasks());
    }

    /**
     * Indicates that this command doesn't terminate the application.
     *
     * @return {@code false} since this command doesn't cause the application to exit.
     */
    @Override
    public boolean isExit() {
        return false;
    }
}
