package duke.command.task;

import java.util.List;
import java.util.Map;

import duke.object.task.ToDo;
import duke.parser.element.CommandElement;
import duke.parser.element.argument.StringArgument;

/**
 * Command to add a new todo.
 */
public class ToDoCommand extends TaskCommand {

    private Map<String, Object> inputs;

    /**
     * Constructs ToDoCommand.
     *
     * @param args The arguments entered by the user.
     */
    public ToDoCommand(Map<String, Object> args) {
        super("todo", args);
    }

    /**
     * @inheritdoc
     */
    @Override
    protected List<CommandElement> getCommandElements() {
        return List.of(new StringArgument("description"));
    }

    /**
     * @inheritdoc
     */
    @Override
    protected ToDo makeTask() {
        assert this.args.get("description") instanceof String;
        return new ToDo((String) this.args.get("description"));
    }

}
