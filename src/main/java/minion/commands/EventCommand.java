package minion.commands;

import java.io.IOException;

import minion.data.TaskList;
import minion.data.task.Event;
import minion.storage.Storage;
import minion.ui.Ui;

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
     * @param ui Ui of chatbot.
     * @param storage Storage of chatbot.
     * @throws IOException if there is IO error.
     */
    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) throws IOException {
        tasks.add(event);
        ui.print(
                "Got it. I've added this task:",
                "\t" + event.toString(),
                "Now you have " + tasks.size() + " tasks in the list."
        );
        storage.writeToFile(tasks);
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
