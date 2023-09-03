package commands;

import functions.*;
import java.io.IOException;
import java.time.LocalDateTime;
import tasks.*;

public class AddCommand extends Command {
    protected String desc;
    protected LocalDateTime first;
    protected LocalDateTime second;

    public AddCommand(String desc) {
        this.desc = desc;
        this.first = null;
        this.second = null;
    }

    public AddCommand(String desc, LocalDateTime date) {
        this.desc = desc;
        this.first = date;
        this.second = null;
    }

    public AddCommand(String desc, LocalDateTime start, LocalDateTime end) {
        this.desc = desc;
        this.first = start;
        this.second = end;
    }
    public void execute(TaskList tasks, Ui ui, Storage storage) throws IOException {
        Task t = tasks.createTask(this.desc, this.first, this.second);
        ui.showTaskMsg(t, tasks);
        storage.saveFiles(tasks.showList());
    }
}
