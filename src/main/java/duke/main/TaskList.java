package duke.main;

import duke.task.Task;

import java.util.ArrayList;

/**
 * Contains a list of tasks, used for manipulating or displaying the list of tasks.
 */
public class TaskList {
    private final ArrayList<Task> tasks;

    public TaskList() {
        this.tasks = new ArrayList<>();
    }

    /**
     * Initialize duke.main.TaskList with an array of tasks.
     *
     * @param tasks Tasks to be initialized with.
     */
    public TaskList(ArrayList<Task> tasks) {
        this.tasks = tasks;
    }

    /**
     * Add task to back of the list.
     *
     * @param task Task to be added.
     */
    public void add(Task task) {
        tasks.add(task);
    }

    /**
     * Removes task by index.
     *
     * @param i index to remove.
     */
    public void remove(int i) {
        tasks.remove(i);
    }

    /**
     * Prints out the list of tasks.
     */
    public void print() {
        if (tasks.isEmpty()) {
            System.out.println("     You have no tasks added yet :(");
        } else {
            for (int i = 0; i < tasks.size(); i++) {
                System.out.println("     " + (i + 1) + ". " + tasks.get(i));
            }
        }
    }

    /**
     * Prints out the list of tasks
     * @param findString filter
     */
    public void print(String findString) {
        boolean found = false;
        if (tasks.isEmpty()) {
            System.out.println("     You have no tasks added yet :(");
        } else {
            for (int i = 0; i < tasks.size(); i++) {
                if (tasks.get(i).task.contains(findString)) {
                    System.out.println("     " + (i + 1) + ". " + tasks.get(i));
                }
            }
        }

        if (!found) {
            System.out.println("No tasks found with string '" + findString + "'");
        }
    }

    /**
     * Prints number of tasks.
     */
    public void printSize(){
        System.out.println("     Now you have " + tasks.size() + " tasks in the list.");
    }

    public void mark(int i) {
        tasks.get(i).mark();
    }

    public void unmark(int i) {
        tasks.get(i).unmark();
    }

    public ArrayList<Task> getList() {
        return tasks;
    }
}
