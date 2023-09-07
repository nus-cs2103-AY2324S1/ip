package duke.commands;

import duke.parsers.ErrorMessage;
import duke.io.Storage;
import duke.tasks.Task;
import duke.tasks.TaskList;
import duke.ui.Ui;

public class DeleteCommand extends Command {
    private final int indexToDelete;

    public DeleteCommand(int index) {
        indexToDelete = index;
    }

    public boolean isValidIndex(TaskList list) {
        return this.indexToDelete >= 0 && this.indexToDelete < list.getNumberOfTasks();
    }

    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) {
        if (tasks.isEmpty()) {
            //if the task list is empty, throw an exception
        }

        if (!isValidIndex(tasks)) {
            //if the index passed is not valid, throw an exception
        } else {
            Task deletedTask = tasks.removeTask(indexToDelete);
            String format = "Noted. I've removed this task:\n %s \n" + "Now you have %d tasks in the list.";
            String message = String.format(format, deletedTask.toString(), tasks.getNumberOfTasks());

            ui.appendResponse(message);
        }

        storage.save(tasks);
    }
}
