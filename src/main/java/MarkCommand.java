/**
 * Marks a task.
 */
public class MarkCommand implements Command {
    private final DukeState state;

    public MarkCommand(DukeState state) {
        this.state = state;
    }

    /**
     * Sets a given task to be marked.
     *
     * @param input The user input of the task to be marked.
     */
    @Override
    public void run(String input) {
        String[] args = input.split(" ", 2);
        String indexString = args[1];
        int index = Integer.parseInt(indexString) - 1;
        Task task = this.state.getTask(index);
        task.mark();
        System.out.printf((DukeConstants.MARKED_MESSAGE) + "%n", task);
    }
}
