package ekud.state;

public final class State {
    private final TaskList taskList;

    public State() {
        taskList = new TaskList();
    }

    public TaskList getTaskList() {
        return taskList;
    }
}
