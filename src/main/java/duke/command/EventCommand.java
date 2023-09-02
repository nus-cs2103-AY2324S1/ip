package duke.command;

import duke.Event;
import duke.ui.TextUi;

/**
 * Adds an event task to the task list.
 */
public class EventCommand extends Command {
    public static final String COMMAND_WORD = "event";
    public static final String MESSAGE_USAGE = "event <description> /from <date> /to <date>";
    private final Event taskToAdd;

    public EventCommand(Event task) {
        taskToAdd = task;
    }

    public String[] execute() {
        String[] response = new String[3];
        this.tasks.add(taskToAdd);

        response[0] = "Got it. I've added this task:";
        response[1] = String.format("%s%s", TextUi.INDENT, taskToAdd);
        response[2] = String.format("Now you have %d task%s in the list.",
                this.tasks.size(),
                this.tasks.size() == 1 ? "" : "s");
        return response;
    }
}
