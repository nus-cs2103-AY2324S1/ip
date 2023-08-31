package duke.command.task;

import duke.exception.DateRangeException;
import duke.object.task.Event;
import duke.object.task.Task;
import duke.parser.element.argument.DateArgument;
import duke.parser.element.argument.StringArgument;
import duke.parser.element.CommandElement;
import duke.parser.element.field.Field;
import java.time.LocalDate;
import java.util.List;
import java.util.Map;

/**
 * Command to add a new event.
 */
public class EventCommand extends TaskCommand {

    /**
     * Constructor for EventCommand.
     * 
     * @param args The arguments entered by the user.
     */
    public EventCommand(Map<String, Object> args) {
        super("event", args);
    }

    /**
     * @inheritdoc
     */
    @Override
    protected List<CommandElement> getCommandElements() {
        return List.of(new StringArgument("description"),
                new Field("/from"), new DateArgument("from"),
                new Field("/to"), new DateArgument("to"));
    }

    /**
     * @inheritdoc
     */
    @Override
    protected Event makeTask() throws DateRangeException {
        return new Event((String) this.args.get("description"), (LocalDate) this.args.get("from"), (LocalDate) this.args.get("to"));
    }

}
