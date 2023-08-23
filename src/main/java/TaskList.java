import java.util.ArrayList;

public class TaskList {
    /** An ArrayList to hold tasks entered by the User. */
    private ArrayList<Task> tasks;

    public TaskList() {
        this.tasks = new ArrayList<Task>();
    }


    /**
     * Adds the given task to the tasks ArrayList.
     * Prints that the task has been added.
     *
     * @param task The task to add.
     *
     */
    public void addTask(String task) {
        tasks.add(new Task(task));
        System.out.println("\tadded: " + task);

    }

    /**
     * Lists the tasks present in tasks ArrayList.
     */
    public void listTasks() {
        for (int i = 0; i < tasks.size(); i++) {
            System.out.print("\t");
            System.out.print(i + 1);
            System.out.print(". " + tasks.get(i) + "\n");
        }

    }

    /**
     * Marks a given Task as done if it exists.
     *
     * @param index The location of the task in tasks ArrayList.
     */
    public void markTask(int index) {
        if (index > tasks.size() || index <= 0) {
            System.out.println("\tInvalid Input. Try again.");

        } else {
            tasks.get(index - 1).markAsDone();
            System.out.println("\tNice! I've marked this task as done:");
            System.out.println("\t  " + tasks.get(index - 1));
        }
    }

    /**
     * Marks a given Task as not done if it exists.
     *
     * @param index The location of the task in tasks ArrayList.
     */
    public void unmarkTask(int index) {
        if (index > tasks.size() || index <= 0) {
            System.out.println("\tInvalid input. Try again.");

        } else {
            tasks.get(index - 1).markAsNotDone();
            System.out.println("\tOk. I have marked this task as not done yet:");
            System.out.println("\t  " + tasks.get(index - 1));
        }

    }
}
