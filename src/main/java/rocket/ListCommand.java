package rocket;

public class ListCommand extends Command{
    public ListCommand() {
        super(false);
    }

    /**
     * Execute list command
     * @param tasks Modifies or uses the tasklist if needed.
     * @param ui Displays to the UI if needed.
     * @param storage Stores and retrieves data from the storage.
     */
    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) {
        ui.showTasks(tasks);
    }

}
