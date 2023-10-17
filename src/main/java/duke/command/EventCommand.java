package duke.command;

import duke.tasks.Event;

/**
 * Adds an event task to the task list.
 */
public class EventCommand extends Command {
    public static final String COMMAND_WORD = "event";
    public static final String MESSAGE_USAGE = "event <description> /from <date YYYY-MM-DD> /to <date YYYY-MM-DD>";
    private final Event taskToAdd;

    public EventCommand(Event task) {
        taskToAdd = task;
    }

    /**
     * Inserts an event task into the task list and returns a response for the user.
     *
     * @return the response to the user.
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
