package Duke.Tasks;

import java.util.ArrayList;

/**
 * Stores the list of tasks that the user has.
 * Provides additional methods for operating on the tasks.
 */
public class TaskList {
    private ArrayList<Task> list = new ArrayList<>();

    /**
     * The constructor for a basic TaskList.
     *
     * @param tasks The default tasks (if any) this should be initialised to
     */
    public TaskList(ArrayList<Task> tasks) {
        this.list = tasks;
    }

    /**
     * Adds an item to the list.
     *
     * @param task The user's task
     */
    public void addToList(Task task) {
        list.add(task);

    }

    /**
     * Removes an item from the list.
     * Indexes start from 1, not zero
     *
     * @param index the index of the task to remove.
     * @return the task that was removed
     */
    public Task removeFromList(int index) {
        Task task = list.get(index - 1);
        list.remove(index - 1);
        return task;
    }

    /**
     * Marks a specific task in the list as done.
     * Indexes start from 1, not 0
     *
     * @param index
     * @return the task that was modified
     */
    public Task markAsDone(int index) {
        Task task = list.get(index - 1);
        task.setDone();
        return task;
    }

    /**
     * Marks a specific task in the list as undone.
     * Indexes start from 1, not 0
     *
     * @param index
     * @return the task that was modified
     */
    public Task markAsUnDone(int index) {
        Task task = list.get(index - 1);
        task.setUnDone();
        return task;
    }

    public int getSize() {
        return this.list.size();
    }

    /**
     * Encodes the current Duke.Tasks in a string, each task separated by a newline.
     * @return
     */
    public String serialize() {
        StringBuilder stringBuilder = new StringBuilder();

        for (int i = 0; i < this.list.size(); i++) {
            stringBuilder.append(this.list.get(i).encodeTask());
            stringBuilder.append("\n");
        }


        return stringBuilder.toString();
    }

    @Override
    public String toString() {
        StringBuilder resultMsg = new StringBuilder();

        for (int i = 0; i < list.size(); i++) {
            resultMsg.append(i + 1).append(". ").append(list.get(i).toString());

            resultMsg.append("\n");
        }

        return resultMsg.toString();
    }

}
