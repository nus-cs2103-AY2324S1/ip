package command;

import exception.DialogixException;
import main.Storage;
import main.TaskList;
import main.Ui;
import task.Deadline;

import java.util.Date;

public class AddDeadlineCommand extends Command {
    private String description;
    private String deadlineBy;
    private Date deadlineDate;

    public AddDeadlineCommand(String description, String deadlineBy) {
        this.description = description;
        this.deadlineBy = deadlineBy;
    }

    public AddDeadlineCommand(String description, Date deadlineDate) {
        this.description = description;
        this.deadlineDate = deadlineDate;
    }

    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) throws DialogixException {
        tasks.addToStack();
        Deadline deadline = validateDeadline();
        tasks.add(deadline);
        ui.printAddSuccessMessage(deadline, tasks.size());
        storage.save(tasks.getAllTasks());
    }

    Deadline validateDeadline() {
        Deadline deadline;
        if (deadlineDate == null) {
            deadline = new Deadline(description, deadlineBy);
        } else {
            deadline = new Deadline(description, deadlineDate);
        }
        return deadline;
    }
}
