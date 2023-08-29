import java.util.ArrayList;

/**
 * Contains functionality to read and interact with the application state.
 */
public class DukeState {
    private final ArrayList<Task> tasks = new ArrayList<>();

    /**
     * Inserts a task into the list of items.
     *
     * @param task The item to be added.
     */
    public void insertTask(Task task) {
        tasks.add(task);
    }

    /**
     * Lists out all the tasks.
     */
    public void listTasks() {
        System.out.println(DukeConstants.LIST_MESSAGE);
        for (int i = 0; i < tasks.size(); i++) {
            Task task = tasks.get(i);
            System.out.printf("\t %d.%s\n", i + 1, task);
        }
        System.out.println("\t" + DukeConstants.HORIZONTAL_LINE);
    }

    /**
     * Gets the number of tasks.
     *
     * @return The number of tasks.
     */
    public int getTaskCount() {
        return this.tasks.size();
    }

    /**
     * Retrieves task based on index.
     *
     * @param index The index of the item to be retrieved.
     */
    public Task getTask(int index) {
        return this.tasks.get(index);
    }

    /**
     * Deletes task based on index.
     *
     * @param index The index of the item to be Delete.
     */
    public void deleteTask(int index) {
        Task task = this.tasks.remove(index);
        System.out.printf((DukeConstants.DELETE_MESSAGE) + "%n",
                task, this.tasks.size());
    }

}
