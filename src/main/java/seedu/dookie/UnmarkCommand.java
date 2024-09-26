package seedu.dookie;

/**
 * Encapsulates the Unmark Command.
 */
public class UnmarkCommand extends Command {
    private Parser parser;
    private TaskList tasks;
    private Ui ui;

    /**
     * Creates a new UnmarkCommand instance.
     *
     * @param parser The parser being used.
     * @param tasks The accumulated tasks.
     * @param ui The Ui being used.
     */
    public UnmarkCommand(Parser parser, TaskList tasks, Ui ui) {
        this.parser = parser;
        this.tasks = tasks;
        this.ui = ui;
    }

    /**
     * Executes the code corresponding to an unmark command.
     *
     * @param cmd The user input.
     * @return A string containing the unmark task message.
     * @throws InvalidDescriptionException When there is only one word in the user input.
     * @throws InvalidIntegerException When the argument specified is not an integer.
     * @throws InvalidTaskNumberException When the integer specified is not a task number.
     * @throws InvalidDataFormatException When the format dookie.txt file is incorrect.
     */
    public String execute(String cmd) throws InvalidDescriptionException,
            InvalidIntegerException,
            InvalidTaskNumberException,
            InvalidDataFormatException {
        if (parser.descriptionIsEmpty(cmd)) {
            throw new InvalidDescriptionException("unmark");
        }

        int taskNumber = -1;
        String integer = cmd.split(" ", 2)[1];

        try {
            taskNumber = Integer.parseInt(integer);
        } catch (Exception e) {
            throw new InvalidIntegerException();
        }

        if (!parser.isValidTaskNumber(taskNumber)) {
            throw new InvalidTaskNumberException(taskNumber);
        }

        Task task = tasks.updateTasks(taskNumber - 1, "UNMARK");
        return ui.getUnmarkedTaskMessage(task);
    }
}
