package ekud.state;

/**
 * Represents all the state contained in the ekud program.
 */
public final class State {
    private final TaskList taskList;

    /**
     * Creates an empty state.
     */
    public State() {
        taskList = new TaskList();
    }

    /**
     * Returns the current list of tasks.
     * 
     * @return The task list.
     */
    public TaskList getTaskList() {
        return taskList;
    }
}
