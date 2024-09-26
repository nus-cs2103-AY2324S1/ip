package seedu.dookie;

import java.time.LocalDateTime;

/**
 * Encapsulates the Deadline command.
 */
public class DeadlineCommand extends Command {
    private Parser parser;
    private TaskList tasks;
    private Ui ui;

    /**
     * Creates a new DeadlineCommand instance.
     *
     * @param parser The parser being used.
     * @param tasks The accumulated tasks.
     * @param ui The Ui being used.
     */
    public DeadlineCommand(Parser parser, TaskList tasks, Ui ui) {
        this.parser = parser;
        this.tasks = tasks;
        this.ui = ui;
    }

    /**
     * Executes the code corresponding to a Deadline command.
     *
     * @param cmd The user input.
     * @return A string containing the add task message.
     * @throws InvalidDescriptionException When there is only one word in the user input.
     * @throws NoDeadlineException When there is no deadline specified.
     * @throws InvalidDeadlineException When the deadline specified is not in the correct format.
     */
    public String execute(String cmd) throws InvalidDescriptionException,
            NoDeadlineException,
            InvalidDeadlineException {
        if (parser.descriptionIsEmpty(cmd)) {
            throw new InvalidDescriptionException("deadline");
        }

        String taskWithDeadline = cmd.split(" ", 2)[1];

        if (parser.hasNoDeadline(taskWithDeadline)) {
            throw new NoDeadlineException();
        }

        String taskName = taskWithDeadline.split("/", 2)[0];
        String deadlineDescription = taskWithDeadline.split("/", 2)[1];

        LocalDateTime dateTime = parser.checkDeadline(deadlineDescription);
        if (dateTime == null) {
            throw new InvalidDeadlineException(deadlineDescription);
        }
        Task deadline = new Deadline(taskName, dateTime);
        tasks.addTask(deadline);
        return ui.getAddTaskMessage(deadline, tasks);
    }
}
