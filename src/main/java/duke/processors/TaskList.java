package duke.processors;

import duke.task.Task;
import java.util.ArrayList;

/**
 * This class is used to store Task class in a ArrayList and perform
 * operations on the list.
 */
public class TaskList {

    private final ArrayList<Task> TASKS = new ArrayList<>();
    private int count = 0;

    /**
     * A constructor for TaskList class.
     * @param duke
     */
    public TaskList(FileHandler duke) {
        duke.readFile(TASKS);
        count = TASKS.size();
    }

    /**
     * Return the Task at the index given.
     * @param ind index
     * @return Task
     */
    public Task get(int ind) {

        return this.TASKS.get(ind);
    }

    /**
     * Add a task to the ArrayList.
     * @param task the task to be added
     */
    public void addTasks(Task task) {
        this.TASKS.add(task);
        this.count++;
    }

    /**
     * List all the task in the ArrayList.
     */
    public void listTasks() {
        for (int i = 0; i < this.TASKS.size(); i++) {
            System.out.println((i + 1) + ". " + this.TASKS.get(i));
        }
    }

    /**
     * Delete the task at the given index
     * @param index the index of which task to be deleted
     * @param duke to update the txt file
     */
    public void deleteTask(int index, FileHandler duke) {
        Task delete = this.TASKS.remove(index);
        this.count--;

        System.out.println("Noted. I've removed this task:");
        System.out.println("    " + delete.toString());
        System.out.println("Now you have " + this.count + " TASKS in the list.");

        duke.DeleteLine(delete.toString());
    }

    /**
     * Return the number of tasks in the list
     * @return integer
     */
    public int getCount() {
        return this.count;
    }
}
