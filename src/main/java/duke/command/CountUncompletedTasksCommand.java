package duke.command;

import duke.tasklist.TaskList;

public class CountUncompletedTasksCommand extends Command {

    private static final String COMMAND_RESPONSE = "You still have %d of %d tasks uncompleted. Jia you! \n";

    @Override
    public String execute(TaskList tasks) {
        return response(tasks);
    }

    private String response(TaskList tasks) {
        return String.format(COMMAND_RESPONSE, tasks.getUncompletedCount(), tasks.getSize());
    }
}
