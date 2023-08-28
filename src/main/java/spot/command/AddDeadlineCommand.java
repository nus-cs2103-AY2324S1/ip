package spot.command;

import java.time.LocalDate;

import spot.Storage;
import spot.TaskList;
import spot.Ui;
import spot.exception.SpotException;
import spot.task.Deadline;

public class AddDeadlineCommand extends Command {

    private String description;
    private LocalDate deadline;

    public AddDeadlineCommand(String description, LocalDate deadline) {
        this.description = description;
        this.deadline = deadline;
    }

    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) throws SpotException {
        Deadline d = tasks.addDeadline(description, deadline);
        ui.sayAdd(tasks, d);
    }

    @Override
    public boolean isExit() {
        return false;
    }

}
