package duke;

/**
 * A command class that represents the listing of tasks.
 */
public class TaskLister extends Command {
    //The DukeList used to display all tasks
    private TaskList taskList;

    /**
     * Instantiates a TaskLister
     * @param taskList The DukeList used
     */
    public TaskLister(TaskList taskList) {
        this.taskList = taskList;
    }

    /**
     * Returns the full list of tasks.
     * @return The displayed list of tasks.
     */
    @Override
    public String execute() {
        return taskList.display();
    }
}
