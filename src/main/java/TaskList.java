import Tasks.Task;

import java.util.ArrayList;

/**
 * Stores the list of items that the user has.
 */
public class TaskList {
    private ArrayList<Task> list = new ArrayList<>();

    /**
     * Adds an item to the list.
     *
     * @param task The user's task
     */
    public void addToList(Task task) {
        list.add(task);

        System.out.println("\uD83D\uDE0A I've added a new task: " + task.toString());
    }

    /**
     * Removes an item from the list.
     *
     * @param name The name of the item to remove
     */
    public void removeFromList(String name) {
        list.removeIf(item -> item.getItemName().equals(name));
    }

    /**
     * Marks a specific task in the list as done.
     * Indexes start from 1, not 0
     *
     * @param index
     */
    public void markAsDone(int index) {
        list.get(index - 1).setDone();
    }

    /**
     * Marks a specific task in the list as undone.
     * Indexes start from 1, not 0
     *
     * @param index
     */
    public void markAsUnDone(int index) {
        list.get(index - 1).setUnDone();
    }

    public int getSize() {
        return this.list.size();
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
