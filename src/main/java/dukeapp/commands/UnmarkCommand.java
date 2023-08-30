package dukeapp.commands;

import dukeapp.DukeState;

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
        this.state.unmarkTask(index);
    }
}
