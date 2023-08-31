package duke.command;

import duke.tasks.Event;
import duke.tasks.TaskList;
import duke.util.Storage;
import duke.util.Ui;

/**
 * Represents a command to add an event task to the task list.
 * <p>
 * The {@code AddEventCommand} class encapsulates the details of the event,
 * including its description and the duration it spans (from-to). When executed,
 * the event is added to the task list and the list is saved using the provided storage.
 * </p>
 */
public class AddEventCommand extends Command {

    /**
     * Starting time/date of the event.
     */
    private final String from;

    /**
     * Ending time/date of the event.
     */
    private final String to;

    /**
     * Description of the event.
     */
    private final String description;

    /**
     * Constructs a new {@code AddEventCommand} with the provided description
     * and the duration (from-to) it spans.
     *
     * @param description description of the event task.
     * @param from starting time/date of the event.
     * @param to ending time/date of the event.
     */
    public AddEventCommand(String description, String from, String to) {
        this.description = description;
        this.from = from;
        this.to = to;
    }

    /**
     * Executes the command by adding a new event to the provided task list,
     * and saving the updated list using the provided storage.
     *
     * @param tasks list of tasks.
     * @param ui user interface.
     * @param storage storage system.
     */
    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) {
        tasks.addTask(new Event(description, from, to));
        storage.save(tasks.getTasks());
    }

    /**
     * Indicates that this command doesn't terminate the application.
     *
     * @return {@code false} since this command doesn't cause the application to exit.
     */
    @Override
    public boolean isExit() {
        return false;
    }
}
