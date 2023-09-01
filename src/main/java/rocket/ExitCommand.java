package rocket;

import java.io.IOException;

public class ExitCommand extends Command{
    public ExitCommand () {
        super(true);
    }

    /**
     * Executes Exit command
     * @param tasks Modifies or uses the tasklist if needed.
     * @param ui Displays to the UI if needed.
     * @param storage Stores and retrieves data from the storage.
     */
    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) {
        try {
            storage.saveTaskList(tasks);
        } catch (IOException e) {
            ui.showError(e.getMessage());
        }
        System.out.println("    Bye. Hope to see you again soon!");
    }
}
