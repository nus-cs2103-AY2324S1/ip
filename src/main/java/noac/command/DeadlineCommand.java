package noac.command;

import noac.Storage;
import noac.TaskList;
import noac.Ui;
import noac.task.Deadline;

import java.time.LocalDateTime;

public class DeadlineCommand extends Command {

    private String description;
    private LocalDateTime by;

    public DeadlineCommand(String description, LocalDateTime by) {
        this.description = description;
        this.by = by;
    }

    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage){

        Deadline d = new Deadline(this.description, this.by);

        tasks.addTask(d);

        ui.showAddTask(d, tasks.size());

        storage.save(tasks);
    }
}
