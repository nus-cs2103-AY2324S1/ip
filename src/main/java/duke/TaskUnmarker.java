package duke;

/**
 * Represents the command marking a specific task.
 */
public class TaskUnmarker extends Command {
    //The index of the task to be unmarked.
    private int index;

    //The DukeList used to unmark said task.
    private TaskList taskList;

    /**
     * Instantiates an instance of the TaskUnmarker
     * @param index The index of the task to be unmarked.
     * @param taskList The DukeList used to search said task.
     */
    public TaskUnmarker(int index, TaskList taskList) {
        this.index = index;
        this.taskList = taskList;
    }

    /**
     * Unmarks the task within the DukeList
     * @return The message confirming the unmarked task
     */
    @Override
    public String execute() {
        return taskList.unmark(index);
    }
}
