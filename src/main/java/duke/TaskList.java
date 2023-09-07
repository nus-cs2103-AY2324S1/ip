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
    public String mark(int i) { //need handling
        tasks.get(i - 1).setDone();
        return "Nice! I've marked this task as done:\n  "
                + tasks.get(i - 1).toString();
    }

    /**
     * Marks a task as not done based on its index.
     * @param i The index (1-based) of the task to be marked as not done.
     */
    public String unmark(int i) {
        tasks.get(i - 1).setNotDone();
        return "OK, I've marked this task as not done yet:\n  "
                + tasks.get(i - 1).toString();
    }

    /**
     * Lists all the tasks currently in the TaskList.
     */
    public String list() {
        if (counter == 0) {
            return "There is no task in your list yet.\n";
        } else {
            String ans = "Here are the tasks in your list:\n";
            for (int i = 1; i <= counter; i++) {
                ans += (i + "." + tasks.get(i - 1).toString() + '\n');
            }
            return ans;
        }
    }

    /**
     * Adds a task to the TaskList.
     * @param t The task to be added.
     */
    public String addTask(Task t) {
        tasks.add(t);
        counter += 1;
        saveTask(t);
        return "Got it. I've added this task:\n  "
                + t + "\nNow you have " + counter + " tasks in the list.\n";
    }

    /**
     * Removes a task from the TaskList based on its index.
     * @param index The index (1-based) of the task to be removed.
     */
    public String removeTask(int index) {
        String temp = tasks.get(index - 1).toString();
        tasks.remove(index - 1);
        counter -= 1;
        return "Noted. I've removed this task:\n"
                + temp + "\nNow you have " + counter + " tasks in the list.\n";
    }

    /**
     * Saves a task to the storage.
     * @param t The task to be saved.
     */
    public String saveTask(Task t) {
        return TaskMaster.storage.write(t.getSavingFormat());
    }

    /**
     * Finds a task from the ArrayList based on a key.
     * @param key The keyword to search for within the ArrayList.
     */
    public String findTask(String key) {
        ArrayList<String> lines = new ArrayList<>();
        for (Task task : tasks) {
            if (task.toString().contains(key)) {
                lines.add(task.toString());
            }
        }
        if (lines.isEmpty()) {
            return "There is no matching task in your list!\n";
        } else {
            String ans = "This are the matching tasks in your list:\n";
            for (int i = 1; i <= lines.size(); ++i) {
                ans += (i + "." + lines.get(i - 1) + '\n');
            }
            return ans;
        }
    }

    /**
     * Reads a task from the storage based on a key.
     * @param key The keyword to search for within the storage.
     */
    public String readTask(String key) {
        if (TaskMaster.storage == null) {
            return "There is no previously saved task!\n";
        } else {
            ArrayList<String> lines = TaskMaster.storage.read(key);
            if (!lines.isEmpty()) {
                String ans = "This are the matching tasks in your list:\n";
                for (int i = 1; i <= lines.size(); ++i) {
                    ans += (i + "." + lines.get(i - 1) + '\n');
                }
                return ans;
            }
            return "There is no previously saved task!\n";
        }
    }
}
