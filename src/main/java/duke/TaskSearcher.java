package duke;

/**
 * Represents the task responsible for searching a task.
 */
public class TaskSearcher extends Command {
    //The keyword used in the search.
    private String keyword;

    //The list of tasks used to search tasks.
    private TaskList taskList;

    /**
     * Instantiates an instance of TaskSearcher
     * @param keyword The keyword used for searching
     * @param taskList The DukeList used to search said task.
     */
    public TaskSearcher(String keyword, TaskList taskList) {
        this.keyword = keyword;
        this.taskList = taskList;
    }

    /**
     * Searches all tasks matching the given keyword.
     * @return the list of tasks matching the given keyword.
     */
    @Override
    public String execute() {
        return taskList.search(keyword);
    }
}
