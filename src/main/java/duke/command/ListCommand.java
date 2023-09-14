package duke.command;

import java.time.LocalDate;
import java.time.format.DateTimeParseException;

import duke.Storage;
import duke.TaskList;
import duke.exception.DukeException;
import duke.ui.Ui;

/**
 * Command class to list down all the tasks stored.
 */
public class ListCommand extends Command {
    private String description;

    /**
     * Constructs the list command object with the description.
     *
     * @param description
     */
    public ListCommand(String description) {
        this.description = description;
    }

    /**
     * List all tasks or tasks on a matching the description date.
     *
     * @param tasks   The list of tasks stored in TaskList object.
     * @param ui      The Ui object to interact with user.
     * @param storage The object used to store the tasks in case of changes
     * @throws DukeException
     */
    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) throws DukeException {
        if (this.description != null) {
            // description field can only be used when we try to list tasks on certain date
            try {
                String content = tasks.findTasksOnDate(LocalDate.parse(description));
                ui.display(content);
            } catch (DateTimeParseException e) {
                throw new DukeException("Wrong date format! Please use yyyy-mm-dd format");
            }
        } else {
            ui.display(tasks.toString());
        }
    }

    @Override
    public boolean equals(Object other) {
        if (other == null) {
            return false;
        }

        if (!(other instanceof ListCommand)) {
            return false;
        }

        ListCommand o = (ListCommand) other;
        return this.description.equals(o.description);
    }
}
