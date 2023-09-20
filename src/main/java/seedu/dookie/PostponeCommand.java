package seedu.dookie;

/**
 * Encapsulates the Postpone Command.
 */
public class PostponeCommand extends Command {
    private Parser parser;
    private TaskList tasks;
    private Ui ui;

    /**
     * Creates a new PostponeCommand instance.
     *
     * @param parser The parser being used.
     * @param tasks The accumulated tasks.
     * @param ui The Ui being used.
     */
    public PostponeCommand(Parser parser, TaskList tasks, Ui ui) {
        this.parser = parser;
        this.tasks = tasks;
        this.ui = ui;
    }

    /**
     * Executes the code corresponding to a postpone command.
     *
     * @param cmd The user input.
     * @return A string containing the postpone task message.
     * @throws InvalidDescriptionException When there is only one word in the user input.
     * @throws InvalidIntegerException When the argument specified is not an integer.
     * @throws InvalidTaskNumberException When the integer specified is not a task number.
     * @throws InvalidDataFormatException When the format dookie.txt file is incorrect.
     * @throws NoDeadlineException When no new deadline is specified.
     */
    public String execute(String cmd) throws InvalidDescriptionException,
            InvalidIntegerException,
            InvalidTaskNumberException,
            InvalidDataFormatException,
            NoDeadlineException {
        if (parser.descriptionIsEmpty(cmd)) {
            throw new InvalidDescriptionException("postpone");
        }

        int taskNumber = -1;
        String integer = cmd.split(" ", 3)[1];

        try {
            taskNumber = Integer.parseInt(integer);
        } catch (Exception e) {
            throw new InvalidIntegerException();
        }

        if (!parser.isValidTaskNumber(taskNumber)) {
            throw new InvalidTaskNumberException(taskNumber);
        }

        Task task = tasks.postponeTask(cmd,(taskNumber - 1));
        return ui.getPostponeMessage(task);

    }
}
