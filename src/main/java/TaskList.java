public class TaskList {
    /**
     * A task list that stores the user's tasks.
     */
    private String[] taskList;
    /**
     * An index that tracks the current newest position in the task list.
     */
    private int index;

    /**
     * Constructor for the TaskList class.
     */
    public TaskList() {
        this.taskList = new String[100];
        this.index = 0;
    }

    /**
     * Adds a task to the task list.
     *
     * @param task The task to be added into the task list.
     */
    public void addTask(String task) {
        this.taskList[this.index] = task;
        this.index++;
    }

    /**
     * Prints the contents of the task list in the order they were added.
     */
    public void printTaskList() {
        for (int i = 0; i < this.taskList.length; i++) {
            if (this.taskList[i] != null) {
                System.out.println((i+1) + ". " + this.taskList[i]);
            }
        }
    }
}
