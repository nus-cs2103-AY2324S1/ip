package duke.command;

import duke.tasklist.TaskList;

public class CountTaskTypeCommand extends Command {

    private static final String COMMAND_RESPONSE = "Your tasklist consists of: \n"
            + INDENT + "- %d todos \n"
            + INDENT + "- %d deadlines\n"
            + INDENT + "- %d events\n";

    @Override
    public String execute(TaskList tasks) {
        return response(tasks);
    }

    private String response(TaskList tasks) {
        return String.format(COMMAND_RESPONSE, tasks.getTodoCount(), tasks.getDeadlineCount(), tasks.getEventCount());
    }
}