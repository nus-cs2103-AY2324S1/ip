/**
 * This is the TaskList class
 * @author Selwyn
 */
public class TaskList {
    /**
     * This is the task array
     */
    private String[] tasks;

    /**
     * This keeps track of the number of tasks in the list
     */
    private int numTasks = 0;

    /**
     * Constructor creates an array of size 100 when there is no argument provided
     */
    public TaskList() {
        tasks = new String[100];
    }

    /**
     * Constructor creates an array of a specific size when size is provided
     * @param size
     */
    public TaskList(int size) {
        tasks = new String[size];
    }

    /**
     * This method adds a task to the task list
     * @param task
     */
    public void addTask(String task) {
        tasks[numTasks] = task;
        numTasks++;
        System.out.println("added: " + task);
    }

    /**
     * This method displays and prints all the tasks in the task list
     */
    public void displayTasks() {
        for (int i = 0; i < numTasks; i++) {
            int bullet = i + 1;
            System.out.println(bullet + ". " + tasks[i]);
        }
    }
}
