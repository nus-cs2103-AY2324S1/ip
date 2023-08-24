/**
 * Mark a task.
 */
public class MarkCommand implements Command {
    private final DukeState state;

    public MarkCommand(DukeState state) {
        this.state = state;
    }

    /**
     * Set a given task to be marked.
     *
     * @param input The user input of the task to be marked.
     */
    @Override
    public void run(String input) {
        int index = Integer.parseInt(input) - 1;
        Task task = this.state.getTask(index);
        task.mark();
        System.out.printf((DukeConstants.MARKED_MESSAGE) + "%n",
                task.getStatusIcon(), task);
    }
}
