package duke.command;

import duke.tasklist.TaskList;

public class FindTaskCommand extends Command {

    private static final String COMMAND_RESPONSE = "Here are the matching tasks in your list:\n";

    private String input;

    public FindTaskCommand(String input) {
        this.input = input;
    }

    @Override
    public String execute(TaskList tasks) {
        TaskList matchingTasks = tasks.find(input);
        return response(matchingTasks);
    }

    private String response(TaskList tasks) {
        return COMMAND_RESPONSE + tasks.getFormattedList();
    }
}
