package duke.command;

import duke.tasklist.TaskList;
public class PrintListCommand extends Command {

    private static final String COMMAND_RESPONSE = "Here are the tasks in your list:\n";

    @Override
    public String execute(TaskList tasks) {
        return response(tasks);
    }

    private String response(TaskList tasks) {
        return COMMAND_RESPONSE + tasks.getFormattedList();
    }
}
