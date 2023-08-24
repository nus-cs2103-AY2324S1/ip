import java.util.ArrayList;

public class TaskList {
    /**
     * A task list that stores the user's tasks.
     */
    private ArrayList<Task> taskList;
    /**
     * An index that tracks the current newest position in the task list.
     */
    private int index;

    /**
     * Constructor for the TaskList class.
     */
    public TaskList() {
        this.taskList = new ArrayList<>(100);
        this.index = 0;
    }

    /**
     * Adds a task to the task list.
     * Prints the name of the task added.
     *
     * @param task The task to be added into the task list.
     */
    public void addTask(Task task) {
        this.taskList.add(task);
        this.index++;
        System.out.println("Got it. I've added this task:");
        System.out.println(task.toString());
        System.out.println("Now you have " + this.index + " tasks in the list.");
    }

    /**
     * Prints the contents of the task list in the order they were added.
     */
    public void printTaskList() {
        System.out.println("Here are the tasks in your list:");
        for (int i = 0; i < this.taskList.size(); i++) {
            if (this.taskList.get(i) != null) {
                System.out.println((i+1) + ". " + this.taskList.get(i).toString());
            }
        }
    }

    /**
     * Marks the task with the specified index as done.
     * Prints a notification indicating that the specified task has been marked done.
     *
     * @param index
     */
    public void markDone(int index) {
        try {
            Task task = this.taskList.get(index - 1);
            task.setDone();
            System.out.println("Nice! I've marked this task as done:");
            System.out.println(task);
        } catch (IndexOutOfBoundsException e) {
            System.out.println("☹ OOPS!!! There is no task with this index number.");
        }
    }

    /**
     * Marks the task with the specified index as not done.
     * Prints a notification indicating that the specified task has been marked not done.
     *
     * @param index
     */
    public void markNotDone(int index) {
        try {
            Task task = this.taskList.get(index - 1);
            task.setNotDone();
            System.out.println("OK, I've marked this task as not done yet:");
            System.out.println(task);
        } catch (IndexOutOfBoundsException e) {
            System.out.println("☹ OOPS!!! There is no task with this index number.");
        }
    }
}
