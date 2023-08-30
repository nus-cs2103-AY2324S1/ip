import exceptions.DukeException;

public class ListCommand extends Command {
    public static final String COMMAND_WORD = "list";

    void execute(Tasks tasks, Ui ui, Storage storage, boolean isRestoring) {
        if (!isRestoring) {
            if (tasks.size() == 0) {
                ui.showNoTasks();
            } else {
                ui.showTasks(tasks);
            }
        }
    }
}
