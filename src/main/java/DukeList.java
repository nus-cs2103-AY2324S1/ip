import java.util.ArrayList;
import java.util.List;

/**
 * DukeList is a class that represents a list of tasks.
 */
public class DukeList {
    private List<Task> dukeList;

    /**
     * Constructs a new DukeList object with an empty list of tasks.
     */
    public DukeList() {
        dukeList = new ArrayList<Task>(100);
    }

    /**
     * Adds a task to the list of tasks.
     *
     * @param input The description of the task to be added.
     */
    public void add(String input) {
        Task task = new Task(input);
        dukeList.add(task);
        System.out.println("added: " + input);
    }

    /**
     * Displays the list of tasks along with their corresponding indices.
     */
    public void display() {
        for (int i = 1; i <= dukeList.size(); i++) {
            Task task = dukeList.get(i - 1);
            System.out.println(i + ". " + task.toString());
        }
    }

    /**
     * Marks a task as done based on its index in the list.
     *
     * @param key The index of the task to be marked as done.
     */
    public void markDone(int key) {
        Task task = dukeList.get(key - 1);
        task.markDone();
        System.out.println("Nice! I've marked this task as done:\n" + "\t" + task.toString());
    }

    /**
     * Marks a task as not done based on its index in the list.
     *
     * @param key The index of the task to be marked as not done.
     */
    public void unmark(int key) {
        Task task = dukeList.get(key - 1);
        task.unmark();
        System.out.println("OK, I've marked this task as not done yet:\n" + "\t" + task.toString());
    }
}
