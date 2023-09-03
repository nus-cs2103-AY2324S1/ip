package minion.commands;

import java.io.IOException;

import minion.data.TaskList;
import minion.data.task.Event;
import minion.storage.Storage;
import minion.utils.StringFormatter;

/**
 * Represents an event command.
 */
public class EventCommand extends Command {
    public static final String COMMAND_WORD = "event";
    private final Event event;

    /**
     * Constructs an event command object.
     * @param event Event of the command.
     */
    public EventCommand(Event event) {
        this.event = event;
    }

    /**
     * Executes the event command.
     * @param tasks Task list.
     * @param storage Storage of chatbot.
     * @throws IOException if there is IO error.
     */
    @Override
    public CommandResult execute(TaskList tasks, Storage storage) throws IOException {
        tasks.add(event);
        storage.writeToFile(tasks);
        return new CommandResult(
            StringFormatter.format(
                "Got it. I've added this task:",
                "\t" + event.toString(),
                "Now you have " + tasks.size() + " tasks in the list."
            )
        );
    }

    /**
     * Checks whether this object equals the other object.
     * @param o other object.
     * @return whether this object equals the other object.
     */
    @Override
    public boolean equals(Object o) {
        if (o == null || !(o instanceof EventCommand)) {
            return false;
        }
        EventCommand c = (EventCommand) o;
        return this.event.equals(c.event);
    }
}
