package tasket.command;

import tasket.storage.Storage;
import tasket.data.TaskList;
import tasket.exception.TasketException;
import tasket.ui.Ui;

public class UnmarkCommand extends Command {

    public UnmarkCommand(String arguments) {
        super(arguments);
    }

    @Override
    public void execute(TaskList taskList, Ui ui, Storage storage) throws TasketException {
        if (commandDescription.isEmpty()) {
            throw new TasketException("The task index cannot be empty");
        }

        try {
            int i = Integer.parseInt(commandDescription);

            if (i <= 0) {
                throw new TasketException("The task index cannot be less than 1");
            } else if (i > taskList.size()) {
                throw new TasketException("The task index cannot exceed the list");
            }

            taskList.unmark(i - 1);
            ui.showUnmarkedTask(taskList.getTaskString(i - 1));
            storage.rewriteSaveFile(taskList);
        } catch (NumberFormatException e) {
            throw new TasketException("The task index must be a number");
        }
    }
}
