package alcazar.Commands;

import alcazar.Storage;
import alcazar.TaskList;

public class ExitCommand extends Command {
    @Override
    public String execute(TaskList tasks,
                          Storage storage) {
        return "Goodbye my Master! I hope to be of service soon.";
    }

    @Override
    public boolean isExit() {
        return true;
    }
}
