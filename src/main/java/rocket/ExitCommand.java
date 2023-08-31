package rocket;

import java.io.IOException;

public class ExitCommand extends Command{
    public ExitCommand () {
        super(true);
    }

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
