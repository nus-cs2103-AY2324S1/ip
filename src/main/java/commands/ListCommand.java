package commands;

import tasks.TaskList;

public class ListCommand extends Command {

    public static final String COMMAND_WORD = "list";

    @Override
    public void execute(TaskList tasks) {
        tasks.listTasks();
    }
}
