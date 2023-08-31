package duke.command;

import duke.DukeException;
import duke.Storage;
import duke.TaskList;
import duke.Ui;
import duke.task.Deadline;

import java.io.IOException;

public class AddDeadlineCommand extends Command {
    private String taskName;
    private String dueDate;

    /**
     * Constructor for the duke.command.AddDeadlineCommand class.
     *
     * @param taskName
     * @param dueDate
     */
    public AddDeadlineCommand(String taskName, String dueDate) {
        this.taskName = taskName;
        this.dueDate = dueDate;
    }

    @Override
    public void execute(TaskList taskList, Ui ui, Storage storage) throws DukeException {
        try {
            Deadline deadline = new Deadline(taskName, dueDate, false);
            taskList.addTask(deadline);
            storage.appendToFile(deadline.displayableForm());
            ui.successfulAddTaskMsg(deadline.displayableForm(), taskList.getIndex());
        } catch (IOException e) {
            throw new DukeException("Something went wrong: " + e.getMessage());
        }
    }

    public boolean isExit() {
        return false;
    }
}
