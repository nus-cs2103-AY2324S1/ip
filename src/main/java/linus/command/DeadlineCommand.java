package linus.command;

import linus.exception.LinusException;
import linus.task.Deadline;
import linus.task.TaskList;
import linus.util.Ui;

/**
 * Represents a command to add a deadline task.
 */
public class DeadlineCommand extends Command {
    private static final String DEADLINE_INCORRECT_FORMAT =
            "☹ OOPS!!! Please specify the deadline in the correct format: "
                    + "deadline <description> /by <date>";
    private TaskList tasks = null;
    private String data = "";
    private Ui ui = null;

    /**
     * Constructor for DeadlineCommand.
     * @param tasks
     * @param data
     * @param ui
     */
    public DeadlineCommand(TaskList tasks, String data, Ui ui) {
        this.tasks = tasks;
        this.data = data;
        this.ui = ui;
    }

    /**
     * Adds a deadline task to the task list.
     * @throws LinusException
     */
    @Override
    public void execute() throws LinusException {
        String[] items = data.split(" /by ");
        if (items.length != 2) {
            throw new LinusException(
                    DEADLINE_INCORRECT_FORMAT
            );
        }
        String description = items[0];
        String by = items[1];

        tasks.add(new Deadline(description, by));
        ui.printAddSuccessMessage(tasks.get(tasks.getList().size() - 1), tasks.getSize());
    }

}

