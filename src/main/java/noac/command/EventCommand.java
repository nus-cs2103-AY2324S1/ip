package noac.command;

import noac.Storage;
import noac.TaskList;
import noac.Ui;
import noac.task.Event;

import java.time.LocalDateTime;

public class EventCommand extends Command {

    private String description;
    private LocalDateTime from;
    private LocalDateTime to;

    public EventCommand(String description, LocalDateTime from, LocalDateTime to) {
        this.description = description;
        this.from = from;
        this.to = to;
    }

    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) {

        Event e = new Event(this.description, this.from, this.to);

        tasks.addTask(e);

        ui.showAddTask(e, tasks.size());

        storage.save(tasks);

    }
}
