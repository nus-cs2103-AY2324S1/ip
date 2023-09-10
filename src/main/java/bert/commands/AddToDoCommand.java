package bert.commands;

import bert.tasks.ToDo;

public class AddToDoCommand extends AddCommand {
    public static final String COMMAND_WORD = "todo";

    public AddToDoCommand(String description) {
        super(new ToDo(description));
    }
}
