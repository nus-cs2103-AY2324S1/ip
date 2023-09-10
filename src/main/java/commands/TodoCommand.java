package commands;

import tasks.TaskList;

/**
 * This child class instructs the application to create a ToDo object
 * to add to the task list.
 */
public class TodoCommand extends Command {

    public static final String COMMAND_WORD = "todo";
    private String description;

    /**
     * Constructs a Todo object.
     *
     * @param input
     */
    public TodoCommand(String input) {
        this.description = input;
    }

    @Override
    public String execute(TaskList tasks) {
        return tasks.addTodoTask(description);
    }
}
