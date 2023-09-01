package command;

import duke.Storage;
import duke.Task;
import duke.TaskList;
import duke.UI;

import java.time.LocalDate;
import java.util.ArrayList;

public class OnCommand extends Command{
    public static final String COMMAND_ON = "on";

    public OnCommand(ArrayList<String> params) {
        super(params);
    }

    @Override
    public void execute(TaskList tasks, UI ui, Storage storage) {
        LocalDate date = LocalDate.parse(params.get(1));
        ArrayList<Task> tasksOnDate = tasks.getTasksOn(date);
        ui.printTasksOn(tasksOnDate);
    }

    @Override
    public boolean isExit() {
        return false;
    }
}
