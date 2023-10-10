package duke.command;

import duke.tasklist.TaskList;

public class FilterCompletedTasksCommand extends Command {

    private static final String COMMAND_RESPONSE = "Congratulations! You have completed %d of %d tasks!";

    @Override
    public String execute(TaskList tasks) {
        return response(tasks, tasks.getCompletedTasks());
    }

    private String response(TaskList tasks, TaskList completedTasks) {
        return String.format(COMMAND_RESPONSE, completedTasks.getSize(), tasks.getSize())
                + completedTasks.getFormattedList();
    }
}
