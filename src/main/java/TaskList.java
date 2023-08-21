/**
 * This is the TaskList class
 * @author Selwyn
 */
public class TaskList {
    /**
     * This is the Task array
     */
    protected Task[] tasks;

    /**
     * This keeps track of the number of tasks in the list
     */
    protected int numTasks;

    /**
     * Constructor creates an array of size 100 when there is no argument provided
     */
    public TaskList() {
        this.tasks = new Task[100];
        numTasks = 0;
    }

    /**
     * Constructor creates an array of a specific size when size argument is provided
     * @param size
     */
    public TaskList(int size) {
        this.tasks = new Task[size];
        numTasks = 0;
    }

    /**
     * This method adds a task to the task list
     * @param task
     */
    public void addTask(String task) {
        this.tasks[numTasks] = new Task(task);
        this.numTasks++;
        System.out.println("added: " + task);
    }

    /**
     * Marks a specific task in the task list as done
     * @param number
     */
    public void markTaskDone(int number) {
        this.tasks[number - 1].markDone();
        System.out.println("Nice! I've marked this task done: ");
        this.tasks[number - 1].displayTask();
    }

    /**
     * Marks a specific task in the task list as undone
     * @param number
     */
    public void markTaskUndone(int number) {
        this.tasks[number - 1].markUndone();
        System.out.println("Ok, I've marked this task as not done yet: ");
        this.tasks[number - 1].displayTask();
    }

    /**
     * This method displays and prints all the tasks in the task list
     */
    public void displayTasks() {
        for (int i = 0; i < this.numTasks; i++) {
            int bullet = i + 1;
            System.out.print(bullet + ". ");
            this.tasks[i].displayTask();
        }
    }
}