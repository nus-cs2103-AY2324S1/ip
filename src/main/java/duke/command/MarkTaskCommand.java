package duke.command;

import duke.data.exception.DukeException;
import duke.tasklist.TaskList;
import duke.data.task.Task;

public class MarkTaskCommand extends Command {

    private static final String COMMAND_RESPONSE
            = "Nice! I've marked this task as done:\n"
            + INDENT + "%s\n";


    private String taskIndex;

    public MarkTaskCommand(String taskIndex) {
        this.taskIndex = taskIndex;
    }

    @Override
    public String execute(TaskList tasks) {
        try {
            Task markedTask = tasks.markTask(taskIndex);
            return response(markedTask);
        } catch (DukeException e) {
            System.err.println(e.getMessage());
        }
        return null;
    }

    private String response(Task task) {
        return String.format(COMMAND_RESPONSE, task.getFormattedTask());
    }
}
