package duke.command;

import java.time.LocalDate;

import duke.storage.Storage;
import duke.task.TaskList;
import duke.ui.Ui;


/**
 * Represents a command to add a deadline task to Duke's task list.
 */
public class DeadlineCommand extends Command {

    private String description;
    private LocalDate by;

    /**
     * Constructs a DeadlineCommand with the specified description and due date.
     *
     * @param description The description of the deadline task.
     * @param by          The due date of the deadline task.
     */
    public DeadlineCommand(String description, LocalDate by) {
        this.description = description;
        this.by = by;
    }

    @Override
    public boolean isExit() {
        return false;
    }

    @Override
    public void execute(TaskList taskList, Ui ui, Storage storage) {
        ui.showDeadlineMessage(taskList.addDeadline(this.description, this.by), taskList.getSize());
    }
}
