package duke.command;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import duke.object.TaskList;
import duke.object.task.Event;
import duke.object.task.Task;
import duke.parser.element.CommandElement;
import duke.parser.element.argument.DateArgument;
import duke.storage.Storage;
import duke.ui.Ui;

/**
 * Command to list all events on a given date.
 */
public class OngoingCommand extends Command {

    /**
     * Constructs OngoingCommand.
     *
     * @param args The arguments entered by the user.
     */
    public OngoingCommand(Map<String, Object> args) {
        super("ongoing", args);
    }

    /**
     * @inheritdoc
     */
    @Override
    protected List<CommandElement> getCommandElements() {
        return List.of(new DateArgument("date"));
    }

    /**
     * @inheritdoc
     */
    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) {
        assert this.args.get("date") instanceof LocalDate;
        LocalDate date = (LocalDate) this.args.get("date");
        List<Task> filteredEvents = new ArrayList<>();
        for (Task task : tasks) {
            if ((task instanceof Event) && ((Event) task).isOngoing(date)) {
                filteredEvents.add(task);
            }
        }
        ui.print(String.format("Here are the ongoing events on %s:\n%s",
                ui.stringifyDate(date), ui.stringifyList(filteredEvents)));
    }

}
