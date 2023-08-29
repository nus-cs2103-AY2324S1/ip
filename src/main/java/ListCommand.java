/**
 * Lists items from application state.
 */
public class ListCommand implements Command {
    private final DukeState state;

    public ListCommand(DukeState state) {
        this.state = state;
    }

    /**
     * Lists all the items in the application state.
     *
     * @param input The user input.
     */
    @Override
    public void run(String input) {
        this.state.listTasks();
    }
}

