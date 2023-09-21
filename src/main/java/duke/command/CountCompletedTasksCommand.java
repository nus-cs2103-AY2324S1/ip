package duke.command;

import duke.tasklist.TaskList;

public class CountCompletedTasksCommand extends Command {

    private static final String COMMAND_RESPONSE = "Congratulations! You have completed %d of %d tasks!\n";

    @Override
    public String execute(TaskList tasks) {
        return response(tasks);
    }

    private String response(TaskList tasks) {
        return String.format(COMMAND_RESPONSE, tasks.getCompletedCount(), tasks.getSize());
    }
}
