package duke.command.task;

import duke.object.task.Deadline;
import duke.object.task.Task;
import duke.parser.element.argument.DateArgument;
import duke.parser.element.argument.StringArgument;
import duke.parser.element.CommandElement;
import duke.parser.element.field.Field;
import java.time.LocalDate;
import java.util.List;
import java.util.Map;

/**
 * Command to add a new deadline.
 */
public class DeadlineCommand extends TaskCommand {

    /**
     * Constructor for DeadlineCommand.
     * 
     * @param args The arguments entered by the user.
     */
    public DeadlineCommand(Map<String, Object> args) {
        super("deadline", args);
    }

    /**
     * @inheritdoc
     */
    @Override
    protected List<CommandElement> getCommandElements() {
        return List.of(new StringArgument("description"),
                new Field("/by"), new DateArgument("by"));
    }

    /**
     * @inheritdoc
     */
    @Override
    protected Deadline makeTask() {
        return new Deadline((String) this.args.get("description"), (LocalDate) this.args.get("by"));
    }

}
