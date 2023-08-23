/**
 * Represents a list of tasks and provides methods to manipulate them
 */
public class TaskList {
    private int taskCount;
    private final Task[] tasks;
    private final int MAX_TASKS = 100;

    /**
     * Initializes an empty task list with a maximum capacity.
     */
    public TaskList() {
        tasks = new Task[MAX_TASKS];
        taskCount = 0;
    }


    /**
     * Checks if the given task index is valid within the task list.
     *
     * @param taskIndex The index of the task to be checked.
     * @return True if the task index is valid, otherwise false.
     */
    public boolean isValidListIndex(int taskIndex) {
        return (taskIndex >= 0 && taskIndex < taskCount);
    }

    /**
     * Retrieves details of a specific task within the task list.
     *
     * @param taskListIndex The index of the task in the task list.
     * @return Details of the task as a formatted string, or null if the index is invalid.
     */
    public String getTaskDetails(int taskListIndex) {
        if(isValidListIndex(taskListIndex)) {
            Task task = tasks[taskListIndex];
            return task.toString();
        } else {
            System.out.println("Invalid Index of task!");
            return null;
        }
    }

    /**
     * Gets the current count of tasks in the task list.
     *
     * @return The count of tasks in the task list.
     */
    public int getTaskCount() {
        return this.taskCount;
    }

    /**
     * Adds a task to the task list if space is available.
     *
     * @param task The task to be added to the task list.
     */
    public void addTask(Task task) {
        if (taskCount >= MAX_TASKS) {
            System.out.println("No more space to add Task :((");
        } else {
            this.tasks[taskCount] = task;
            this.taskCount++;
        }
    }

    /**
     * Marks a task in the task list as done.
     *
     * @param taskListIndex The index of the task to be marked as done.
     */
    public void markTaskAsDone(int taskListIndex) {
        if (isValidListIndex(taskListIndex)) {
            this.tasks[taskListIndex].markAsDone();
        }
    }

    /**
     * Marks a task in the task list as not done.
     *
     * @param taskListIndex The index of the task to be marked as not done.
     */
    public void markTaskAsNotDone(int taskListIndex) {
        if (isValidListIndex(taskListIndex)) {
            this.tasks[taskListIndex].markAsNotDone();
        }
    }

    /**
     * Displays the tasks in the task list with their details.
     */
    public void displayTasks() {
        if (taskCount == 0) {
            System.out.println("____________________________________________________________");
            System.out.println("Horray!! No tasks in the task list!");
            System.out.println("____________________________________________________________");
        } else {
            System.out.println("____________________________________________________________");
            System.out.println("Here are the tasks in your list:");
            for (int i = 0; i < taskCount; i++) {
                System.out.println((i + 1) + ". " + this.getTaskDetails(i));
            }
            System.out.println("____________________________________________________________");
        }

    }
}
