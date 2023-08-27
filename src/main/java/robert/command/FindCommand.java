package robert.command;

import robert.exception.RobertException;
import robert.storage.Storage;
import robert.task.Task;
import robert.task.TaskList;
import robert.ui.Ui;


public class FindCommand extends Command {

    private final String keyword;

    public FindCommand(String keyword) {
        this.keyword = keyword;
    }

    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) throws RobertException {
        int taskIndex = 0;
        StringBuilder taskListing = new StringBuilder("Below is/are the task(s) associated with '"
                + this.keyword + "':\n");
        for (Task task : tasks) {
            if (task.getDescription().contains(this.keyword)) {
                taskIndex++;
                taskListing.append(String.format("%d. %s\n", taskIndex, task));
            }
        }

        if (taskIndex != 0) {
            ui.showMessage(taskListing.toString());
            return;
        }

        ui.showMessage("Sorry, there are no tasks that are associated with '"
                + this.keyword + "'.");
    }

}
