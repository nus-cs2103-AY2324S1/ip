package duke;

import java.util.ArrayList;
import java.util.Iterator;
/**
 * Represents a list of tasks.
 */
public class TaskList implements Iterable<Task> {
    private ArrayList<Task> tasks;

    /**
     * Initializes an empty task list.
     */
    public TaskList() {
        this.tasks = new ArrayList<>();
    }

    /**
     * Initializes the task list with the given tasks.
     *
     * @param tasks The initial list of tasks.
     */
    public TaskList(ArrayList<Task> tasks) {
        this.tasks = tasks;
    }

    /**
     * Adds a task to the list.
     *
     * @param task The task to be added.
     */
    public void add(Task task) {
        tasks.add(task);
    }

    /**
     * Retrieves a task from the list.
     *
     * @param index The index of the task to be retrieved.
     * @return The task at the specified index.
     */
    public Task get(int index) {
        return tasks.get(index);
    }

    /**
     * Deletes a task from the list.
     *
     * @param index The index of the task to be deleted.
     */
    public void delete(int index) {
        tasks.remove(index);
    }

    /**
     * Retrieves the number of tasks in the list.
     *
     * @return The number of tasks in the list.
     */
    public int size() {
        return tasks.size();
    }

    /**
     * Marks a task as done.
     *
     * @param index The index of the task to be marked as done.
     */
    public void markAsDone(int index) {
        tasks.get(index).markAsDone();
    }

    /**
     * Unmarks a task as done.
     *
     * @param index The index of the task to be unmarked.
     */
    public void unmarkAsDone(int index) {
        tasks.get(index).unmarkAsDone();
    }

    /**
     * Processes a task based on user input.
     *
     * @param userInput The user's input.
     * @param tasks The list of tasks.
     * @param mark Whether to mark the task as done.
     */
    public static void processTask(String userInput, ArrayList<Task> tasks, boolean mark) {
        int taskNumber;
        try {
            taskNumber = Integer.parseInt(userInput.split(" ")[1]);
            if (taskNumber <= 0 || taskNumber > tasks.size()) {
                throw new IndexOutOfBoundsException();
            }

            Task task = tasks.get(taskNumber - 1);
            if (mark) {
                task.markAsDone();
                System.out.println("____________________________________________________________");
                System.out.println("Nice! I've marked this task as done:");
            } else {
                task.unmarkAsDone();
                System.out.println("____________________________________________________________");
                System.out.println("OK, I've marked this task as not done yet:");
            }
            System.out.println("  [" + task.getStatusIcon() + "] " + task.description);
            System.out.println("____________________________________________________________");
        } catch (NumberFormatException e) {
            System.out.println("Please provide a valid task number.");
        } catch (IndexOutOfBoundsException e) {
            System.out.println("That task doesn't exist!");
        }
    }

    /**
     * Returns an iterator over the elements in the task list.
     *
     * @return An iterator over the tasks.
     */
    @Override
    public Iterator<Task> iterator() {
        return tasks.iterator();
    }
}
