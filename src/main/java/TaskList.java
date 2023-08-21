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
        this.numTasks = 0;
    }

    /**
     * This method creates a Todo task
     * @param detail
     */
    public void createTask(String detail) {
        this.tasks[numTasks] = new Todo(detail);
        addTask();
    }

    /**
     * This method creates a Deadline task
     * @param detail
     * @param endDateTime
     */
    public void createTask(String detail, String endDateTime) {
        this.tasks[numTasks] = new Deadline(detail, endDateTime);
        addTask();
    }

    /**
     * This method creates an Event task
     * @param detail
     * @param startDateTime
     * @param endDateTime
     */
    public void createTask(String detail, String startDateTime, String endDateTime) {
        this.tasks[numTasks] = new Event(detail, startDateTime, endDateTime);
        addTask();
    }

    /**
     *  This method adds the most newly created task to the task list
     */
    public void addTask() {
        System.out.println("Got it. I've added this task:");
        System.out.print("   ");
        this.tasks[numTasks].displayTask();

        this.numTasks++;
        if (numTasks == 1) {
            System.out.println("Now you have " + numTasks + " task in the list.");
        } else {
            System.out.println("Now you have " + numTasks + " tasks in the list.");
        }
    }

    /**
     * Marks a specific task in the task list as done
     * @param number
     */
    public void markTaskDone(int number) {
        this.tasks[number - 1].markDone();
        System.out.println("Nice! I've marked this task done:");
        this.tasks[number - 1].displayTask();
    }

    /**
     * Marks a specific task in the task list as undone
     * @param number
     */
    public void markTaskUndone(int number) {
        this.tasks[number - 1].markUndone();
        System.out.println("Ok, I've marked this task as not done yet:");
        this.tasks[number - 1].displayTask();
    }

    /**
     * This method displays and prints all the tasks in the task list
     */
    public void displayTaskList() {
        if (this.numTasks == 0 || this.numTasks == 1) {
            System.out.println("Here is the task in your list:");
        } else {
            System.out.println("Here are the tasks in your list:");
        }
        for (int i = 0; i < this.numTasks; i++) {
            int bullet = i + 1;
            System.out.print(bullet + ". ");
            this.tasks[i].displayTask();
        }
    }
}