package duke.command.task;

import duke.object.task.Task;
import duke.object.task.ToDo;
import duke.parser.element.argument.StringArgument;
import duke.parser.element.CommandElement;
import java.util.List;
import java.util.Map;

public class ToDoCommand extends TaskCommand {

    private Map<String, Object> inputs;
    
    public ToDoCommand(Map<String, Object> args) {
        super("todo", args);
    }

    @Override
    public List<CommandElement> getCommandElements() {
        return List.of(new StringArgument("description"));
    }

    @Override
    public ToDo makeTask() {
        return new ToDo((String) this.args.get("description"));
    }

}
