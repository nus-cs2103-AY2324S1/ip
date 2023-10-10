package bert.commands;

import bert.tasks.ToDo;

/**
 * Represents a command that adds a todo task.
 */
public class AddToDoCommand extends AddCommand {
    public static final String COMMAND_WORD = "todo";

    /**
     * Constructs an AddToDoCommand instance containing the todo task to be added.
     *
     * @param description The description of the todo task.
     */
    public AddToDoCommand(String description) {
        super(new ToDo(description));
    }
}
