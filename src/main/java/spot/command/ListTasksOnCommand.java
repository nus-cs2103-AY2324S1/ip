package spot.command;

import java.time.LocalDate;

import spot.Storage;
import spot.TaskList;
import spot.Ui;

public class ListTasksOnCommand extends Command {

    private LocalDate date;

    public ListTasksOnCommand(LocalDate date) {
        this.date = date;
    }

    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) {
        ui.listTasks(ui, tasks, date);
    }

    @Override
    public boolean isExit() {
        return false;
    }

}
