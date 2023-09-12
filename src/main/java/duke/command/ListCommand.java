package duke.command;

import duke.Storage;
import duke.task.TaskList;

public class ListCommand extends Command {

    @Override
    public String execute(TaskList tasks, Storage storage) {
        return tasks.listAllTask();
    }
}
