package duke.command;

import duke.tasklist.TaskList;

public class PrintStatsCommand extends Command {

    private static final String COMMAND_RESPONSE = "Your tasklist consists of: \n"
            + INDENT + "- %d todos \n"
            + INDENT + "- %d deadlines\n"
            + INDENT + "- %d events\n"
            + "You are %d%% done with all tasks.\n";

    @Override
    public String execute(TaskList tasks) {
        return response(tasks.getTodos(), tasks.getDeadlines(), tasks.getEvents(), tasks.percentDone());
    }

    private String response(TaskList todos, TaskList deadlines, TaskList events, int percentDone) {
        return String.format(COMMAND_RESPONSE, todos.getSize(), deadlines.getSize(), events.getSize(), percentDone);
    }
}