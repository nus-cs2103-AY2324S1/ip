package duke.command;

import duke.tasks.Todo;

/**
 * Represents a task that has to be done.
 */
public class TodoCommand extends Command {
    public static final String COMMAND_WORD = "todo";
    public static final String MESSAGE_USAGE = "todo <description>";
    public static final String MESSAGE_EMPTY_DESCRIPTION = "Sorry, but the description of a todo cannot be empty.";
    private final Todo taskToAdd;

    public TodoCommand(Todo task) {
        taskToAdd = task;
    }

    /**
     * Inserts a Todo task into the task list and returns a response for the user.
     *
     * @return the response to the user
     */
    public String[] execute() {
        String[] response = new String[3];
        this.duke.getTasks().add(taskToAdd);

        response[0] = "Got it. I've added this task:";
        response[1] = taskToAdd.toString();
        response[2] = String.format("Now you have %d task%s in the list.",
                this.duke.getTasks().size(),
                this.duke.getTasks().size() == 1 ? "" : "s");
        return response;
    }
}
