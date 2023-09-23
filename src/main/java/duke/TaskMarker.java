package duke;

/**
 * Represents the command marking a specific task.
 */
public class TaskMarker extends Command {
    //The index of the task to be marked.
    private int index;

    //The DukeList used to mark said task
    private TaskList taskList;

    /**
     * Instantiates an instance of the TaskMarker
     * @param index The index of the task to be marked.
     * @param taskList The DukeList used to search said task.
     */
    public TaskMarker(int index, TaskList taskList) {
        this.index = index;
        this.taskList = taskList;
    }

    /**
     * Marks the task within the DukeList
     * @return The message confirming the marked task
     */
    @Override
    public String execute() {
        return taskList.mark(index);
    }
}
