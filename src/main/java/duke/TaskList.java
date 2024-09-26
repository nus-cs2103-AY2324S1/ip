package duke;

import java.util.ArrayList;

/**
 * Represents a list of tasks. Supports operations to add, remove, mark, unmark, list, save, and read tasks.
 */
public class TaskList {
    /** An ArrayList storing all the Task instances */
    protected ArrayList<Task> tasks;
    /** Parameter keeping track of the number of Task instances in the Arraylist */
    protected int counter;

    /**
     * Initializes an empty TaskList.
     */
    public TaskList() {
        this.tasks = new ArrayList<>();
        this.counter = 0;
    }

    /**
     * Initializes a TaskList using an existing list of tasks.
     *
     * @param old An ArrayList of tasks to be added to the TaskList.
     */
    public TaskList(ArrayList<Task> old) {
        this.tasks = old;
        this.counter = this.tasks.size();
    }

    /**
     * Marks a task as done based on its index.
     * @param i The index (1-based) of the task to be marked as done.
     *
     * @return A message of confirmation.
     */
    public String mark(int i) {
        assert i > 0 && i <= tasks.size() : "Task index out of bounds!";

        tasks.get(i - 1).setDone();
        return "Nice! I've marked this task as done:\n  "
                + tasks.get(i - 1).toString();
    }

    /**
     * Marks a task as not done based on its index.
     * @param i The index (1-based) of the task to be marked as not done.
     *
     * @return A message of confirmation.
     */
    public String unmark(int i) {
        assert i > 0 && i <= tasks.size() : "Task index out of bounds!";

        tasks.get(i - 1).setNotDone();
        return "OK, I've marked this task as not done yet:\n  "
                + tasks.get(i - 1).toString();
    }

    /**
     * Lists all the tasks currently in the TaskList.
     *
     * @return A message of confirmation.
     */
    public String list() {
        assert counter == tasks.size() : "Mismatch between counter and task list size!";

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
     *
     * @param t The task to be added.
     * @return A message of confirmation.
     */
    public String addTask(Task t) {
        assert t != null : "Task should not be null!";

        tasks.add(t);
        counter += 1;
        return "Got it. I've added this task:\n  "
                + t + "\nNow you have " + counter + " tasks in the list.\n";
    }

    /**
     * Removes a task from the TaskList based on its index.
     * @param index The index (1-based) of the task to be removed.
     *
     * @return A message of confirmation.
     */
    public String removeTask(int index) {
        assert index > 0 && index <= tasks.size() : "Task index out of bounds!";

        String temp = tasks.get(index - 1).toString();
        tasks.remove(index - 1);
        counter -= 1;
        return "Noted. I've removed this task:\n"
                + temp + "\nNow you have " + counter + " tasks in the list.\n";
    }

    /**
     * Finds a task from the ArrayList based on a key.
     * @param key The keyword to search for within the ArrayList.
     *
     * @return A message of confirmation.
     */
    public String findTask(String key) {
        assert key != null && !key.trim().isEmpty() : "Search key should not be null or empty!";

        ArrayList<String> lines = new ArrayList<>();
        for (Task task : tasks) {
            if (task.toString().contains(key)) {
                lines.add(task.toString());
            }
        }
        if (lines.isEmpty()) {
            return "There is no matching task in your list!\n";
        } else {
            String ans = "These are the matching tasks in your list:\n";
            for (int i = 1; i <= lines.size(); ++i) {
                ans += (i + "." + lines.get(i - 1) + '\n');
            }
            return ans;
        }
    }

    /**
     * Peek the notes for a task from the TaskList based on its index.
     * @param index The index (1-based) of the task to be removed.
     *
     * @return A message of confirmation.
     */
    public String peekNotes(int index) {
        assert index > 0 && index <= tasks.size() : "Task index out of bounds!";

        Task targetTask = tasks.get(index - 1);
        String notes = targetTask.getNotes();
        if (notes.isEmpty()) {
            return "You have not added any notes for the task:\n" + targetTask.toString();
        }
        return "Your notes for the task:\n" + targetTask.toString()
                + "\n is: " + targetTask.getNotes();
    }

    /**
     * Add notes for a task from the TaskList based on its index.
     * @param index The index (1-based) of the task to be removed.
     * @param notes The notes of the task to be added.
     *
     * @return A message of confirmation.
     */
    public String editNotes(int index, String notes) {
        assert index > 0 && index <= tasks.size() : "Task index out of bounds!";

        Task targetTask = tasks.get(index - 1);
        targetTask.editNotes(notes);
        if (notes.isEmpty()) {
            return "Your have successfully deleted the notes for the task: \n"
                    + targetTask.toString();
        }
        return "Your notes for the task:\n" + targetTask.toString()
                + "\n is successfully updated! Current notes:\n" + targetTask.getNotes();
    }

    /**
     * Returns the list of tasks in the TaskList.
     *
     * @return An ArrayList of tasks.
     */
    public ArrayList<Task> getTasks() {
        return this.tasks;
    }
}
