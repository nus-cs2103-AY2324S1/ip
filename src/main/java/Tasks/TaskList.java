package Tasks;
import java.util.ArrayList;

public class TaskList {
    private ArrayList<Task> enteredText = new ArrayList();

    /**
     * Adds a task to the task list.
     *
     * @param task The task to be added.
     */
    public void add(Task task) {
        enteredText.add(task);
    }

    /**
     * Returns the number of tasks in the task list.
     *
     * @return The number of tasks.
     */
    public int size() {
        return enteredText.size();
    }

    /**
     * Removes a task from the task list.
     *
     * @param task The task to be removed.
     */
    public void remove(Task task) {
        enteredText.remove(task);
    }

    /**
     * Prints the list of tasks with their corresponding indices.
     */
    public void printList() {
        for (int i = 0; i < enteredText.size(); i++) {
            System.out.printf("%d. %s \n", i + 1, enteredText.get(i).toString());
        }
    }

    /**
     * Retrieves a task from the task list by its index.
     *
     * @param index The index of the task to retrieve.
     * @return The task at the specified index.
     */
    public Task retrieve(int index) {
        return enteredText.get(index);
    }
}
