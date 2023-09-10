package command;

import enums.CommandWord;
import parser.Parser;
import tasks.TaskList;
import tasks.TodoTask;

/**
 * The `TodoCommand` class represents a command to create a new todo task.
 * When executed, it parses the command, validates it, and adds
 * a new todo task to the task list if the command is valid.
 */
public class TodoCommand extends Command {
    private final boolean valid;
    private String description;

    /**
     * Constructs a new `TodoCommand` with the specified raw command string.
     *
     * @param rawCommand The raw command string.
     */
    public TodoCommand(String rawCommand) {
        super(rawCommand);
        this.valid = validate(rawCommand);
    }

    /**
     * Validates the "todo" command.
     * It checks if the command is correctly formatted.
     *
     * @param rawCommand The raw command string.
     * @return `true` if the command is valid, `false` otherwise.
     */
    public static boolean validate(String rawCommand) {
        String[] args = Parser.getArgs(rawCommand);
        if (args.length != 2) {
            return false;
        }

        return CommandWord.commandWordToValueMap(args[0]).equals(CommandWord.TODO);
    }

    /**
     * Deconstructs the command to extract the description.
     *
     * @param rawCommand The raw command string.
     */
    private void deconstruct(String rawCommand) {
        if (!this.valid) {
            return;
        }
        this.description = Parser.getArgs(rawCommand)[1];
    }

    /**
     * Executes the "todo" command. It parses the command, validates it, and adds a new
     * todo task to the task list if the command is valid.
     *
     * @param taskList The task list to which the todo task is added.
     */
    public void execute(TaskList taskList) {
        if (!this.valid) {
            return;
        }
        this.deconstruct(super.getRawCommand());
        taskList.addTask(new TodoTask(this.description));
    }
}
