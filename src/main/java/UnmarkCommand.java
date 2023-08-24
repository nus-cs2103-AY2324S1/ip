/**
 * Unmark a task.
 */
public class UnmarkCommand implements Command {
    private final DukeState state;

    public UnmarkCommand(DukeState state) {
        this.state = state;
    }

    /**
     * Set a given task to be unmarked.
     *
     * @param input The user input of the task to be unmarked.
     */
    @Override
    public void run(String input) {
        int index = Integer.parseInt(input) - 1;
        Task task = this.state.getTask(index);
        task.unmark();
        System.out.printf((DukeConstants.UNMARKED_MESSAGE) + "%n",
                task.getStatusIcon(), task);
    }
}
