package duke.command;

import duke.DukeException;
import duke.task.Deadline;
import duke.TaskList;
import duke.Ui;
import duke.Storage;

import java.time.LocalDateTime;

public class AddDeadlineCommand extends Command {
    private String description;
    private String deadlineString;
    private LocalDateTime deadlineDate;

    public AddDeadlineCommand(String description, String deadline) {
        this.description = description;
        this.deadlineString = deadline;
    }

    public AddDeadlineCommand(String description, LocalDateTime deadlineDate) {
        this.description = description;
        this.deadlineDate = deadlineDate;
    }

    @Override
    public void execute(TaskList taskList, Ui ui, Storage storage) throws DukeException {
        Deadline deadline;
        if (deadlineDate == null) {
            deadline = new Deadline(description, deadlineString);
        } else {
            deadline = new Deadline(description, deadlineDate);
        }
        taskList.add(deadline);
        ui.addToListSuccess(deadline, taskList.size());
        storage.saveList(taskList.getAllTasks());
    }
}
