package duke.command;

import duke.exception.DukeException;
import duke.list.TaskList;
import duke.storage.Storage;
import duke.task.Task;
import duke.ui.Ui;

public class RescheduleCommand extends Command {

    public static final String COMMAND_WORD = "reschedule";

    private int index;
    private String deadline;

    public RescheduleCommand(int index, String deadline) {
        this.index = index;
        this.deadline = deadline;
    }

    /**
     * @inheritDoc
     */
    @Override
    public String execute(TaskList taskList, Ui ui, Storage storage) throws DukeException {
        Task task = taskList.rescheduleTask(index, deadline);
        storage.write(taskList);
        return ui.showRescheduleMessage(task);
    }
}
