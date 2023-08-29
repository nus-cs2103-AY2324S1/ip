package grumpygordon.commands;

import grumpygordon.storage.Storage;
import grumpygordon.tasks.*;
import grumpygordon.ui.*;

import java.time.LocalDateTime;

public class EventCommand extends Command {
    private final String description;
    private final LocalDateTime from;
    private final LocalDateTime to;

    public EventCommand(String description, LocalDateTime from, LocalDateTime to) {
        this.description = description;
        this.from = from;
        this.to = to;
    }
    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) {
        tasks.addTask(new Event(this.description, this.from, this.to, false));
        ui.showCommandMessage("     grumpygordon.tasks.Event task added to list\n     "
                + tasks.getTask(tasks.size() - 1).toString() + "\n");
        storage.saveTasks(tasks);
    }
}
