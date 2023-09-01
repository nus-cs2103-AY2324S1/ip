package tasks;
import java.util.ArrayList;

public class TaskList {
    private ArrayList<Task> enteredTexts = new ArrayList<>();

    /**
     * Adds a task to the task list.
     *
     * @param task The task to be added.
     */
    public void add(Task task) {
        enteredTexts.add(task);
    }

    /**
     * Returns the number of tasks in the task list.
     *
     * @return The number of tasks.
     */
    public int size() {
        return enteredTexts.size();
    }

    /**
     * Removes a task from the task list.
     *
     * @param task The task to be removed.
     */
    public void remove(Task task) {
        enteredTexts.remove(task);
    }

    /**
     * Prints the list of tasks with their corresponding indices.
     */
    public void printList() {
        for (int i = 0; i < enteredTexts.size(); i++) {
            System.out.printf("%d. %s \n", i + 1, enteredTexts.get(i).toString());
        }
    }

    /**
     * Retrieves a task from the task list by its index.
     *
     * @param index The index of the task to retrieve.
     * @return The task at the specified index.
     */
    public Task retrieve(int index) {
        return enteredTexts.get(index);
    }

    /**
     * Finds matching tasks and prints them
     *
     * @param find The corresponding items in the list to find.
     */
    public void find(String find) {
        int matchCounter = 0;
        for (Task task : enteredTexts) {
            if (task.getName().contains(find)) {
                System.out.println(task);
            }
        }
        if (matchCounter == 0) {
            System.out.println("No matching tasks!");
        }
    }
}
