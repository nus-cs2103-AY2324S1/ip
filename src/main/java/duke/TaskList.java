package duke;

import java.util.ArrayList;

/**
 * Represents a list of tasks. Supports operations to add, remove, mark, unmark, list, save, and read tasks.
 */
public class TaskList {
    /** An ArrayList storing all the Task instances */
    protected ArrayList<Task> tasks = new ArrayList<>();
    /** Parameter keeping track of the number of Task instances in the Arraylist */
    protected int counter = 0;

    /**
     * Marks a task as done based on its index.
     * @param i The index (1-based) of the task to be marked as done.
     */
    public void mark(int i) { //need handling
        tasks.get(i-1).setDone();
        System.out.println("Nice! I've marked this task as done:\n  "
                + tasks.get(i-1).toString());
    }

    /**
     * Marks a task as not done based on its index.
     * @param i The index (1-based) of the task to be marked as not done.
     */
    public void unmark(int i) {
        tasks.get(i-1).setNotDone();
        System.out.println("OK, I've marked this task as not done yet:\n  "
                + tasks.get(i-1).toString());
    }

    /**
     * Lists all the tasks currently in the TaskList.
     */
    public void list() {
        if (counter == 0) {
            System.out.println("There is no task in your list yet.");
        } else {
            System.out.println("Here are the tasks in your list:");
            for (int i = 1; i <= counter; i++) {
                System.out.println(i + "." + tasks.get(i - 1).toString());
            }
        }
    }

    /**
     * Adds a task to the TaskList.
     * @param t The task to be added.
     */
    public void addTask(Task t) {
        tasks.add(t);
        counter += 1;
        saveTask(t);
        System.out.println("Got it. I've added this task:\n  "
                + t + "\nNow you have " + counter + " tasks in the list.");
    }

    /**
     * Removes a task from the TaskList based on its index.
     * @param index The index (1-based) of the task to be removed.
     */
    public void removeTask(int index) {
        String temp = tasks.get(index-1).toString();
        tasks.remove(index-1);
        counter -= 1;
        System.out.println("Noted. I've removed this task:\n"
                + temp + "\nNow you have " + counter + " tasks in the list.");
    }

    /**
     * Saves a task to the storage.
     * @param t The task to be saved.
     */
    public void saveTask(Task t) {
        TaskMaster.storage.write(t.getSavingFormat());
    }

    /**
     * Finds a task from the ArrayList based on a key.
     * @param key The keyword to search for within the ArrayList.
     */
    public void findTask(String key) {
        ArrayList<String> lines = new ArrayList<>();
        for (Task task : tasks) {
            if (task.toString().contains(key)) {
                lines.add(task.toString());
            }
        }
        if (lines.isEmpty()) {
            System.out.println("There is no matching task in your list!");
        } else {
            System.out.println("This are the matching tasks in your list:");
            for (int i = 1; i <= lines.size(); ++i) {
                System.out.println(i + "." + lines.get(i-1));
            }
        }
    }

    /**
     * Reads a task from the storage based on a key.
     * @param key The keyword to search for within the storage.
     */
    public void readTask(String key) {
        if (TaskMaster.storage == null) {
            System.out.println("There is no previously saved task!");
        } else {
            ArrayList<String> lines = TaskMaster.storage.read(key);
            if (!lines.isEmpty()) {
                System.out.println("This are the matching tasks in your list:");
                for (int i = 1; i <= lines.size(); ++i) {
                    System.out.println(i + "." + lines.get(i-1));
                }
            }
        }
    }
}
