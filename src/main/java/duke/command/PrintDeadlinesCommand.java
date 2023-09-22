package duke.command;

import duke.tasklist.TaskList;

public class PrintDeadlinesCommand extends Command {

    private static final String COMMAND_RESPONSE = "You have %d deadlines on your list!";

    @Override
    public String execute(TaskList tasks) {
        return response(tasks.getDeadlines());
    }

    private String response(TaskList deadlines) {
        return String.format(COMMAND_RESPONSE, deadlines.getSize()) + deadlines.getFormattedList();
    }
}
