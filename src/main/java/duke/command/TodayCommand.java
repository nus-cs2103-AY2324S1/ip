package duke.command;

import duke.Storage;
import duke.Ui;
import duke.task.TaskList;

import java.time.LocalDate;

/**
 * Represents a command to display tasks for today.
 */
public class TodayCommand extends Command {

    @Override
    public void execute(TaskList taskList, Ui ui, Storage storage) {
        LocalDate today = LocalDate.now();
        ui.showTasksForToday(today, taskList);
    }

    @Override
    public boolean isExit() {
        return false;
    }
}
