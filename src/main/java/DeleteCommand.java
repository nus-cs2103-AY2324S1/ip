/**
 * Deletes a task.
 */
public class DeleteCommand implements Command {
    private final DukeState state;

    public DeleteCommand(DukeState state) {
        this.state = state;
    }

    /**
     * Deletes a task from the application.
     *
     * @param input The user input of the task to be deleted.
     */
    @Override
    public void run(String input) {
        String[] args = input.split(" ", 2);
        String indexString = args[1];
        int index = Integer.parseInt(indexString) - 1;
        this.state.deleteTask(index);
    }
}
