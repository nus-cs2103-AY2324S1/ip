package duke.commands;

import java.io.IOException;
import java.util.Date;

import duke.data.task.Deadline;
import duke.data.TaskList;
import duke.ui.Ui;
import duke.storage.Storage;

public class AddDeadlineCommand extends Command {
    /** Description of the deadline task */
    private final String description;
    /** Date of deadline */
    private final Date by;

    /**
     * Constructor to initialize AddDeadlineCommand.
     *
     * @param description Description of the deadline task.
     * @param by Date of deadline.
     */
    public AddDeadlineCommand(String description, Date by) {
        this.description = description;
        this.by = by;
    }

    @Override
    public void execute(TaskList taskList, Ui ui, Storage storage) throws IOException {
        Deadline newDeadline = new Deadline(description, by);
        taskList.addTask(newDeadline);
        Storage.save(newDeadline);
        ui.showTaskAdded(newDeadline, taskList.countTasks());
    }
}
