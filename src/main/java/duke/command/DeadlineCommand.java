package duke.command;

import duke.main.Storage;
import duke.main.TaskList;
import duke.main.Ui;
import duke.task.Deadline;

import duke.exception.DukeException;

public class DeadlineCommand extends Command {
    private String description;
    private String deadlineBy;

    public DeadlineCommand(String description, String deadlineBy) {
        this.description = description;
        this.deadlineBy = deadlineBy;
    }

    @Override
    public void execute(TaskList taskList, Ui ui, Storage storage) throws DukeException {
        Deadline deadline = new Deadline(description, deadlineBy);
        taskList.add(deadline);
        ui.displayCompletionMessage(deadline, taskList.size());
        storage.saveTasksToFile(taskList);
    }

}
