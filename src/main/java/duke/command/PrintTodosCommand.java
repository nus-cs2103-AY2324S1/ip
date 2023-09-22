package duke.command;

import duke.tasklist.TaskList;

public class PrintTodosCommand extends Command {

    private static final String COMMAND_RESPONSE = "You have %d todos on your list!";

    @Override
    public String execute(TaskList tasks) {
        return response(tasks.getTodos());
    }

    private String response(TaskList todos) {
        return String.format(COMMAND_RESPONSE, todos.getSize()) + todos.getFormattedList();
    }
}
