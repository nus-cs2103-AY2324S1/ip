package duke.command;

import java.io.IOException;

import duke.main.DukeException;
import duke.main.Storage;
import duke.main.TaskList;
import duke.main.Ui;
import duke.task.Deadline;

/**
 * The AddDeadlineCommand class.
 */
public class AddDeadlineCommand extends Command {
    private String taskName;
    private String dueDate;

    /**
     * Constructor for the duke.command.AddDeadlineCommand class.
     *
     * @param taskName
     * @param dueDate
     */
    public AddDeadlineCommand(String taskName, String dueDate) throws DukeException {
        if (taskName.equals("")) {
            throw new DukeException("OOPS!!! Incorrect description of a deadline.");
        }
        this.taskName = taskName;
        this.dueDate = dueDate;
    }

    /**
     * Executes the command by creating a deadline, adding it to the taskList,
     * adding it to the storage, and displaying a message to the ui.
     */
    @Override
    public String execute(TaskList taskList, Ui ui, Storage storage) throws DukeException {
        try {
            Deadline deadline = new Deadline(taskName, dueDate, false);
            taskList.addTask(deadline);
            storage.rewriteToFile(taskList.getList());
            return ui.successfulAddTaskMsg(deadline.userDisplayString(), taskList.getIndex());
        } catch (IOException e) {
            throw new DukeException("Something went wrong: " + e.getMessage());
        }
    }

    public boolean isExit() {
        return false;
    }
}
