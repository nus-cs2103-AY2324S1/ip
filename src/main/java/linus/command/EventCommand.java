package linus.command;

import linus.exception.LinusException;
import linus.task.Event;
import linus.task.TaskList;
import linus.util.Ui;

/**
 * Represents a command to add an event.
 */
public class EventCommand extends Command {
    private static final String EVENT_INCORRECT_FORMAT =
            "☹ OOPS!!! Please specify the event in the correct format: \n"
                    + "event <description> /from <date> /to <date>";
    private TaskList tasks = null;
    private String data = "";
    private Ui ui = null;

    /**
     * Constructor for EventCommand.
     * @param tasks
     * @param data
     * @param ui
     */
    public EventCommand(TaskList tasks, String data, Ui ui) {
        this.tasks = tasks;
        this.data = data;
        this.ui = ui;
    }

    /**
     * Adds an event to the task list.
     * @throws LinusException
     */
    @Override
    public void execute() throws LinusException {
        String[] items = data.split(" /from | /to ");
        if (items.length != 3) {
            throw new LinusException(
                    EVENT_INCORRECT_FORMAT
            );
        }
        String description = items[0];
        String from = items[1];
        String to = items[2];

        tasks.add(new Event(description, from, to));
        ui.printAddSuccessMessage(tasks.get(tasks.getList().size() - 1), tasks.getSize());
    }
}


