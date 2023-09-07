package duke.command;

import duke.DukeException;
import duke.task.Task;
import duke.task.TaskList;
import duke.ui.Ui;

/**
 * This command functions to return all tasks that happen on specified date.
 */
public class DateCommand extends Command {
    private String date;

    public DateCommand(String date) {
        this.date = date;
    }

    @Override
    public String execute(TaskList tasks, Ui ui) throws DukeException {
        TaskList tasksToday = new TaskList();
        for (Task task: tasks) {
            if (task.isToday(date)) {
                tasksToday.add(task);
            }
        }
        return ui.find(tasksToday, date);
    }
}
