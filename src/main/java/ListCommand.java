public class ListCommand extends Command {

    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) throws RobertException {
        if (tasks.getTaskCount() == 0) {
            ui.showMessage("You do not have any tasks stored. Add one!");
            return;
        }

        int taskIndex = 1;
        StringBuilder taskListing = new StringBuilder("Here are the tasks in your list:\n");
        for (Task task : tasks) {
            taskListing.append(String.format("%d. %s\n", taskIndex, task));
            taskIndex++;
        }
        ui.showMessage(taskListing.toString());
    }
}
