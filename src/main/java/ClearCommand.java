public class ClearCommand extends Command {

    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) throws RobertException {
        tasks.clearTasks();
        ui.showMessage("Understood. I've removed every task in your list.\n"
                + "Now your list of tasks is empty!");
    }
}
