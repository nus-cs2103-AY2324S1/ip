package commands;

import java.time.LocalDateTime;

import data.TaskList;
import data.exception.DukeException;
import data.tasks.Deadline;
import data.tasks.Task;
import storage.Storage;
import ui.Ui;

public class DeadlineCommand extends Command {
    private String description;
    private LocalDateTime deadline;

    public DeadlineCommand(String description,
                           LocalDateTime deadline) {
        this.description = description;
        this.deadline = deadline;
    }

    @Override
    public void execute(TaskList tasks,
            Storage storage,
            Ui ui) throws DukeException {
        Task dl = new Deadline(description, deadline);
        tasks.add(dl);
        storage.update(tasks);
        ui.displayMsg(new String[] {
            "Okie! I've added a new " + Ui.cTxt("DEADLINE", Ui.Color.BLUE) + ":",
            "  " + dl.toString(),
            "Total no. of tasks stored: " + tasks.getSize()
        });
    }
}
