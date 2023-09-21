package duke.command;

import duke.data.exception.CCException;
import duke.tasklist.TaskList;
import duke.data.task.Task;

public class DeleteTaskCommand extends Command {

    private static final String COMMAND_RESPONSE
            = "Noted. I've removed this task:\n"
            + INDENT + "%s\n"
            + "Now you have %d tasks in the list.";


    private String taskIndex;

    public DeleteTaskCommand(String taskIndex) {
        this.taskIndex = taskIndex;
    }

    @Override
    public String execute(TaskList tasks) {
        try {
            Task deletedTask = tasks.deleteTask(taskIndex);
            return response(tasks, deletedTask);
        } catch (CCException e) {
            System.err.println(e.getMessage());
        }
        return null;
    }

    private String response(TaskList tasks, Task task) {
        return String.format(COMMAND_RESPONSE, task.getTaskForPrinting(), tasks.getSize());
    }
}
