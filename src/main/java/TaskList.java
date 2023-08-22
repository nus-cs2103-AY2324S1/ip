import java.util.ArrayList;

/**
 * This is the TaskList class
 * @author Selwyn
 */
public class TaskList {
    /**
     * This is the Task array
     */
    protected ArrayList<Task> tasks;

    /**
     * This keeps track of the number of tasks in the list
     */
    protected int numTasks;

    /**
     * Constructor creates an array of size 100 when there is no argument provided
     */
    public TaskList() {
        this.tasks = new ArrayList<>();
        numTasks = 0;
    }

    /**
     * This method adds the most newly created task to the task list
     * @param newTask
     */
    public void addTask(Task newTask) {
        System.out.println("Got it. I've added this task:");
        System.out.print("   ");
        newTask.displayTask();

        this.tasks.add(newTask);
        this.numTasks++;

        if (numTasks == 1) {
            System.out.println("Now you have " + numTasks + " task in the list.");
        } else {
            System.out.println("Now you have " + numTasks + " tasks in the list.");
        }
    }

    /**
     * This method removes a specific task from the task list
     * @param index
     */
    public void deleteTask(int index) {
        System.out.println("Noted. I've removed this task:");
        System.out.print("   ");
        this.tasks.get(index - 1).displayTask();

        this.tasks.remove(index - 1);
        this.numTasks--;

        if (numTasks == 1 || numTasks == 0) {
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
        this.tasks.get(number - 1).markDone();
        System.out.println("Nice! I've marked this task done:");
        this.tasks.get(number - 1).displayTask();
    }

    /**
     * Marks a specific task in the task list as undone
     * @param number
     */
    public void markTaskUndone(int number) {
        this.tasks.get(number - 1).markUndone();
        System.out.println("Ok, I've marked this task as not done yet:");
        this.tasks.get(number - 1).displayTask();
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
            this.tasks.get(i).displayTask();
        }
    }

    /**
     * This method checks if number provided exceeds size of task list
     * @param index
     * @return boolean Represents whether provided number exceeds size of task list
     */
    public boolean exceedsSizeOfTaskList(int index) {
        return index > numTasks;
    }
}