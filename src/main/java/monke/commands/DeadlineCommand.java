package monke.commands;

import java.time.LocalDateTime;

import monke.MonkeException;
import monke.Storage;
import monke.TaskList;
import monke.Ui;
import monke.tasks.Deadline;

public class DeadlineCommand extends Command {
    public static final String COMMAND_WORD = "deadline";
    private String description;
    private LocalDateTime date;

    public DeadlineCommand(String description, LocalDateTime date) {
        this.description = description;
        this.date = date;
    }

    @Override
    public void execute(Ui ui, Storage storage, TaskList tasks) throws MonkeException {
        Deadline deadline = new Deadline(this.description, this.date);
        tasks.add(deadline);
        storage.saveData(tasks);

        ui.showAddTask(deadline, tasks.size());
    }
}
