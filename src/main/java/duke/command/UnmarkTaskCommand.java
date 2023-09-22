package duke.command;

import duke.data.exception.DukeException;
import duke.tasklist.TaskList;
import duke.data.task.Task;

public class UnmarkTaskCommand extends Command {

    private static final String COMMAND_RESPONSE
            = "OK, I've marked this task as not done yet:\n"
            + INDENT + "%s\n";


    private String taskIndex;

    public UnmarkTaskCommand(String taskIndex) {
        this.taskIndex = taskIndex;
    }

    @Override
    public String execute(TaskList tasks) {
        try {
            Task unmarkedTask = tasks.unmarkTask(taskIndex);
            return response(unmarkedTask);
        } catch (DukeException e) {
            System.err.println(e.getMessage());
        }
        return null;
    }

    private String response(Task task) {
        return String.format(COMMAND_RESPONSE, task.getFormattedTask());
    }
}
