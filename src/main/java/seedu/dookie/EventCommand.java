package seedu.dookie;

/**
 * Encapsulates the Event Command.
 */
public class EventCommand extends Command{
    private Parser parser;
    private TaskList tasks;
    private Ui ui;

    /**
     * Creates a new EventCommand instance.
     *
     * @param parser The parser being used.
     * @param tasks The accumulated tasks.
     * @param ui The Ui being used.
     */
    public EventCommand(Parser parser, TaskList tasks, Ui ui) {
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
     * @throws IncompleteDurationException When the duration is not correctly specified.
     */
    public String execute(String cmd) throws InvalidDescriptionException,
            IncompleteDurationException {
        if (parser.descriptionIsEmpty(cmd)) {
            throw new InvalidDescriptionException("event");
        }

        String taskWithDuration = cmd.split(" ", 2)[1];
        String[] time = taskWithDuration.split("/");

        // Check if there is a valid duration
        if (time.length != 3) {
            throw new IncompleteDurationException();
        }

        String taskName = time[0];
        String starting = time[1];
        String ending = time[2];

        // Assumes that starting and ending both start with "from" and "to" respectively
        Task event = new Event(taskName, parser.checkStarting(starting), parser.checkEnding(ending));
        tasks.addTask(event);
        return ui.getAddTaskMessage(event, tasks);
    }
}
