package duke.command;

import duke.exception.DukeException;
import duke.Storage;
import duke.TaskList;
import duke.Ui;
import duke.task.Deadline;

import java.time.LocalDate;

public class AddDeadlineCommand extends Command {
    private String description;
    private LocalDate deadlineDate;

    public AddDeadlineCommand(String description, LocalDate deadlineDate) {
        this.description = description;
        this.deadlineDate = deadlineDate;
    }

    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) throws DukeException {
        Deadline deadline = new Deadline(description, deadlineDate);
        tasks.add(deadline);
        ui.printAddSuccessMessage(deadline, tasks.getAllTasks());
        storage.save(tasks.getAllTasks());
    }

    @Override
    public boolean isExit() {
        return false;
    }
}
