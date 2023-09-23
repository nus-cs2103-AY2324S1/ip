package duke;

/**
 * Adds the ToDo into the DukeList.
 */
public class ToDoAdder extends Command {

    //The name of the task.
    private String taskName;
    //The list of tasks.
    private TaskList list;

    /**
     * Instantiates a ToDoAdder task
     * @param taskName The name of the Todo task.
     * @param list The task list.
     */
    public ToDoAdder(String taskName, TaskList list) {
        this.taskName = taskName;
        this.list = list;
    }

    /**
     * Adds the ToDo task into the task list.
     * @return The confirmation of the ToDo's addition into the tasklist.
     */
    @Override
    public String execute() {
        Task todo = new ToDo(taskName);
        return list.store(todo);
    }
}
