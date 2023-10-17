package duke.command;

import duke.tasks.Deadline;

/**
 * Adds a Deadline task to the task list.
 */
public class DeadlineCommand extends Command {
    public static final String COMMAND_WORD = "deadline";
    public static final String MESSAGE_USAGE = "deadline <description> /by <date YYYY-MM-DD>";
    private final Deadline taskToAdd;

    public DeadlineCommand(Deadline task) {
        taskToAdd = task;
    }

    /**
     * Inserts the deadline task into the task list and returns the response for the user.
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
