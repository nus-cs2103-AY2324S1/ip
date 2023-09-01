package duke;

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
}
