package duke.command;

import duke.tasklist.TaskList;

public class FilterUncompletedTasksCommand extends Command {

    private static final String COMMAND_RESPONSE = "You still have %d of %d tasks uncompleted. Jia you!";

    @Override
    public String execute(TaskList tasks) {
        return response(tasks, tasks.getUncompletedTasks());
    }

    private String response(TaskList tasks, TaskList uncompletedTasks) {
        return String.format(COMMAND_RESPONSE, uncompletedTasks.getSize(), tasks.getSize())
                + uncompletedTasks.getFormattedList();
    }
}
