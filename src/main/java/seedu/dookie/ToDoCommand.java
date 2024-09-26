package seedu.dookie;

/**
 * Encapsulates the ToDo Command.
 */
public class ToDoCommand extends Command {
    private Parser parser;
    private TaskList tasks;
    private Ui ui;

    /**
     * Creates a new ToDoCommand instance.
     *
     * @param parser The parser being used.
     * @param tasks The accumulated tasks.
     * @param ui The Ui being used.
     */
    public ToDoCommand(Parser parser, TaskList tasks, Ui ui) {
        this.parser = parser;
        this.tasks = tasks;
        this.ui = ui;
    }

    /**
     * Executes the code corresponding to a ToDo command.
     *
     * @param cmd The user input.
     * @return A string containing the add task message.
     * @throws InvalidDescriptionException When there is only one word in the user input.
     */
    public String execute(String cmd) throws InvalidDescriptionException {
        // Check if description is empty
        if (parser.descriptionIsEmpty(cmd)) {
            throw new InvalidDescriptionException("todo");
        }

        String taskName = cmd.split(" ", 2)[1];
        Task todo = new ToDo(taskName);
        tasks.addTask(todo);
        return ui.getAddTaskMessage(todo, tasks);
    }
}
