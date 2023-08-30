package grumpygordon.commands;

import grumpygordon.storage.Storage;
import grumpygordon.ui.Ui;
import grumpygordon.tasks.*;

import java.time.LocalDateTime;

public class DeadlineCommand extends Command {
    private final String description;
    private final LocalDateTime by;

    public DeadlineCommand(String description, LocalDateTime by) {
        this.description = description;
        this.by = by;
    }
    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) {
        tasks.addTask(new Deadline(this.description, this.by, false));
        ui.showCommandMessage("     Deadline task added to list\n     "
                + tasks.getTask(tasks.size() - 1).toString() + "\n");
        storage.saveTasks(tasks);
    }
}
