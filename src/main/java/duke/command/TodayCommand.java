package duke.command;

import java.time.LocalDate;
import java.util.ArrayList;

import duke.Storage;
import duke.Ui;
import duke.task.Task;
import duke.task.TaskList;

/**
 * Represents a command to display tasks for today.
 */
public class TodayCommand extends Command {

    @Override
    public void execute(TaskList taskList, Ui ui, Storage storage) {
        LocalDate today = LocalDate.now();
        ArrayList<Task> matchingTasks = new ArrayList<>();

        for (int i = 0; i < taskList.getLength(); i++) {
            Task task = taskList.getTask(i);
            if (task.isOnDate(today)) {
                matchingTasks.add(task);
            }
        }

        ui.showTasksForToday(today, matchingTasks);
    }

    @Override
    public boolean isExit() {
        return false;
    }
}
