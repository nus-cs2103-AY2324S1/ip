import java.io.File;
import java.io.IOException;
import java.util.ArrayList;

/**
 * The Task class represents a task with a description and a status indicating whether it's done or not.
 */
abstract public class Task {
    protected String description;
    protected boolean isDone;

    /**
     * Constructs a new task with the given description. By default, the task is marked as not done.
     *
     * @param description The description of the task.
     */
    public Task(String description) {
        this.description = description;
        this.isDone = false;
    }

    /**
     * Returns the status icon of the task. The icon is "X" if the task is done, or a space if it's not done.
     *
     * @return The status icon of the task.
     */
    public String getStatus() {
        return (isDone ? "X" : " ");
    }

    /**
     * Returns the description of the task.
     *
     * @return The description of the task.
     */
    public String toString() {
        return "[" + this.getStatus() + "] " + this.description;
    }

    /**
     * Marks the task as done by setting its status to true.
     */
    public void markTask(ArrayList<Task> store, String[] commandTask) {
        int number = Integer.parseInt(commandTask[1]);
        Task curr = store.get(number - 1);
        curr.isDone = true;
        System.out.println("Nice! I've marked this task as done:");
        System.out.println(curr.toString());
    }

    public void unmarkTask(ArrayList<Task> store, String[] commandTask) {
        int number = Integer.parseInt(commandTask[1]);
        Task curr = store.get(number - 1);
        curr.isDone = false;
        System.out.println("OK, I've marked this task as not done yet:");
        System.out.println(curr.toString());
    }

    public void deleteTask(ArrayList<Task> store, String[] commandTask) {
        int number = Integer.parseInt(commandTask[1]);
        Task curr = store.get(number - 1);
        System.out.println("Noted. I've removed this task:");
        System.out.println("  " + curr.toString());
        store.remove(curr);
        System.out.println("Now you have " + store.size() + " tasks in the list.");
    }

    public abstract void addtoStore() throws IOException;

    public void changeMarktoStore(String[] commandTask) {
        File file = new File("./data/sae.txt");
        int number = Integer.parseInt(commandTask[1]) - 1;
    }

    public abstract String toFileString();

}
