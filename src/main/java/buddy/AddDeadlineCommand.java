package buddy;

import buddy.utils.BuddyException;
import buddy.utils.Storage;
import buddy.utils.Ui;

import java.time.LocalDate;

public class AddDeadlineCommand extends Command {
    private String description;
    private LocalDate deadlineDate;

    public AddDeadlineCommand(String description, LocalDate deadlineDate) {
        this.description = description;
        this.deadlineDate = deadlineDate;
    }

    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) throws BuddyException {
        Deadline deadline = new Deadline(description, deadlineDate, false);
        tasks.addTask(deadline);
        ui.printAddSuccessMessage(deadline, tasks);
        storage.writeToFile(tasks.getAllTasks());
        }
    }
}
