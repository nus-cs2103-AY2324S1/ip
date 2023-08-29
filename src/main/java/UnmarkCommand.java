/**
 * Unmarks a task.
 */
public class UnmarkCommand implements Command {
    private final DukeState state;

    public UnmarkCommand(DukeState state) {
        this.state = state;
    }

    /**
     * Sets a given task to be unmarked.
     *
     * @param input The user input of the task to be unmarked.
     */
    @Override
    public void run(String input) {
        String[] args = input.split(" ", 2);
        String indexString = args[1];
        int index = Integer.parseInt(indexString) - 1;
        Task task = this.state.getTask(index);
        task.unmark();
        System.out.printf((DukeConstants.UNMARKED_MESSAGE) + "%n", task);
    }
}
