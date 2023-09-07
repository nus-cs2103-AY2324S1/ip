package duke.commands;

import duke.parsers.ErrorMessages;
import duke.io.Storage;
import duke.tasks.Task;
import duke.tasks.TaskList;
import duke.ui.Ui;

public class UnmarkCommand extends Command {
    private final int indexToUnmark;

    public UnmarkCommand(int index) {
        indexToUnmark = index;
    }

    public boolean isValidIndex(TaskList list) {
        return indexToUnmark >= 0 && indexToUnmark < list.getNumberOfTasks();
    }

    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) throws Exception {
        if (tasks.isEmpty()) {

        }

        if (!isValidIndex(tasks)) {

        } else {

            Task currentTask = tasks.getTask(indexToUnmark);
            currentTask.unmark();
            String message = "OK, I've marked this task as not done yet:\n " + currentTask;

            ui.appendResponse(message);
            storage.save(tasks);
        }
    }
}
