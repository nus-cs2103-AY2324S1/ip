package duke.command;

import duke.tasklist.TaskList;
import duke.data.task.Task;

public class AddTaskCommand extends Command {

    private static final String COMMAND_RESPONSE
            = "Got it. I've added this task:\n"
            + INDENT + "%s\n"
            + "Now you have %d tasks in the list.";


    private Task task;

    public AddTaskCommand(Task task) {
        this.task = task;
    }

    @Override
    public String execute(TaskList tasks) {
        tasks.addTask(task);
        return response(tasks);
    }

    private String response(TaskList tasks) {
        return String.format(COMMAND_RESPONSE, task.getTaskForPrinting(), tasks.getSize());
    }
}
