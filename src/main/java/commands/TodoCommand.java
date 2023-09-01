package commands;

import tasks.TaskList;

public class TodoCommand extends Command {

    public static final String COMMAND_WORD = "todo";
    private String description;

    public TodoCommand(String input) {
        this.description = input;
    }

    @Override
    public void execute(TaskList tasks) {
        tasks.addTodoTask(description);
    }
}
