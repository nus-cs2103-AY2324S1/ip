package duke.command;

import duke.exception.DukeException;
import duke.storage.Storage;
import duke.task.Event;
import duke.task.TaskList;
import duke.ui.Ui;

/**
 * Represents a command to create an Event object.
 */
public class EventCommand extends Command {
    /** Represents an Event object */
    private Event event;

    /**
     * Constructs the EventCommand.
     *
     * @param input User input.
     * @throws DukeException If any error occurs.
     */
    public EventCommand(String input) throws DukeException {
        if (input == null) {
            // Empty description.
            throw new DukeException(" ☹ OOPS!!! The description of an event cannot be empty.");
        } else if (!input.contains("/from")) {
            // No from date.
            throw new DukeException(" ☹ OOPS!!! From when?");
        } else if (!input.contains("/to")) {
            // No to date.
            throw new DukeException(" ☹ OOPS!!! To when?");
        }
        String[] tokens = input.split("/from");
        this.event = new Event(tokens[0].strip(), tokens[1].split("/to")[0].strip(),
                tokens[1].split("/to")[1].strip());
    }

    /**
     * {@inheritDoc}
     *
     * @param taskList List of Task objects.
     * @param ui UI that the user interact with.
     * @param storage Storage to handle data to and from an external file.
     * @throws DukeException If any error occurs.
     */
    @Override
    public void execute(TaskList taskList, Ui ui, Storage storage) throws DukeException {
        taskList.addTask(event);
        ui.showMessage(String.format("Got it. I've added this task:\n    "
                + "%s\nNow you have %d tasks in the list.", event, taskList.getListSize()));
        storage.appendFile(event);
    }

    /**
     * {@inheritDoc}
     *
     * @param taskList List of Task objects.
     * @param ui UI that the user interact with.
     * @param storage Storage to handle data to and from an external file.
     * @return A String message.
     * @throws DukeException If any error occurs.
     */
    @Override
    public String executeGui(TaskList taskList, Ui ui, Storage storage) throws DukeException {
        taskList.addTask(event);
        storage.appendFile(event);
        return String.format("Got it. I've added this task:\n    "
                + "%s\nNow you have %d tasks in the list.", event, taskList.getListSize());
    }
}
