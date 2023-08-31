package duke.command.task;

import duke.object.task.Task;
import duke.object.task.ToDo;
import duke.parser.element.argument.StringArgument;
import duke.parser.element.CommandElement;
import java.util.List;
import java.util.Map;

/**
 * Command to add a new todo.
 */
public class ToDoCommand extends TaskCommand {

    private Map<String, Object> inputs;

    /**
     * Constructor for ToDoCommand.
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
        return new ToDo((String) this.args.get("description"));
    }

}
