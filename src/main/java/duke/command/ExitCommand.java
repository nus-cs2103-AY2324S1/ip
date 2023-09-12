package duke.command;

import duke.Storage;
import duke.task.TaskList;

public class ExitCommand extends Command {

    @Override
    public String execute(TaskList tasks, Storage storage) {
        return "";
    }
}
