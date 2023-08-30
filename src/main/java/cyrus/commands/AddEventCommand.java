package cyrus.commands;

import cyrus.parser.ParseInfo;
import cyrus.tasks.Event;
import cyrus.tasks.Task;
import cyrus.tasks.TaskList;
import cyrus.ui.Ui;
import cyrus.utility.DateUtility;

import java.time.LocalDate;
import java.util.HashMap;

/**
 * Command to add an {@code Event} to the given {@code TaskList}.
 */
public class AddEventCommand extends Command {
    public AddEventCommand(TaskList taskList, ParseInfo parseInfo) {
        super(taskList, parseInfo);
    }

    /**
     * To add an {@code Event}, must ensure that the event has an argument (i.e. name of the
     * deadline) and it contains a {@code from} and {@code to} option which corresponds to a valid
     * {@code LocalDate} format which is {@code dd/MM/yyyy}.
     *
     * @throws CommandError if no arguments are present, no {@code from} or {@code to} option is
     *                      provided, or date formats are invalid.
     */
    @Override
    public void execute() throws CommandError {
        if (this.parseInfo.hasNoArgument()) {
            throw new CommandError("Event is missing a body!");
        }

        HashMap<String, String> options = this.parseInfo.getOptions();
        if (!options.containsKey("from")) {
            throw new CommandError("Invalid event format: missing /from");
        }
        if (!options.containsKey("to")) {
            throw new CommandError("Invalid event format: missing /to");
        }

        String eventName = this.parseInfo.getArgument();
        String eventFrom = options.get("from");
        String eventTo = options.get("to");

        LocalDate eventFromDate = DateUtility.parse(eventFrom);
        LocalDate eventToDate = DateUtility.parse(eventTo);

        if (eventFrom == null) {
            throw new CommandError("Invalid event format: invalid from string, must be format" +
                    " dd/MM/yyyy");
        }

        Task event = new Event(eventName, eventFromDate, eventToDate);
        this.taskList.addTask(event);
        Ui.printAddTask(event, this.taskList.size());
    }
}
