package sana;

/**
 * Represents a command to add a todo task.
 */
public class AddTodoCommand extends Command {

    /**
     * Constructs an AddTodoCommand object.
     *
     * @param command   The command keyword.
     * @param arguments The arguments provided with the command.
     */
    public AddTodoCommand(String command, String arguments) {
        super(command, arguments);
    }

    /**
     * Executes the AddTodoCommand.
     *
     * @param tasks   The list of tasks.
     * @param storage The storage manager.
     * @return A response message after executing the command.
     * @throws SanaException If there's an error executing the command.
     */
    @Override
    public String execute(TaskList tasks, Storage storage) throws SanaException {
        String arguments = getArguments();

        boolean isDescriptionPresent = !arguments.isBlank();

        if (!isDescriptionPresent) {
            throw new SanaException("OOPS!!! The description of a todo cannot be empty.");
        }

        Task newTodo = new Todo(arguments, false);
        tasks.add(newTodo);
        storage.save(newTodo);

        return ("Got it. I've added this task:\n" + newTodo + "\n"
                    + "Now you have " + tasks.size() + (tasks.size() <= 1 ? " task" : " tasks")
                    + " in the list");
    }

    /**
     * Checks if this command is an exit command.
     *
     * @return Always returns false, as this command is not an exit command.
     */
    @Override
    public boolean isExit() {
        return false;
    }
}
