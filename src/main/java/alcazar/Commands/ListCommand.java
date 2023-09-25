package alcazar.Commands;

import alcazar.Storage;
import alcazar.TaskList;

public class ListCommand extends Command {
    @Override
    public String execute(TaskList tasks,
                          Storage storage) {

        return "Here are the tasks in your list:\n"
                + tasks.getTasks();
    }

    @Override
    public boolean isExit() {
        return false;
    }
}
