package potato.command;

import potato.*;

public class ListCommand extends Command {
    public ListCommand() {
        super.isExit = false;
    }

    @Override
    public String execute(TaskList tasks, Ui ui, Storage storage) {
        return tasks.list();
    }
}
