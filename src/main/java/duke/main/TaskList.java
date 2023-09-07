package duke.main;

import java.util.ArrayList;

import duke.task.Task;


/** Contains a list of tasks, used for manipulating or displaying the list of tasks */
public class TaskList {
    private final ArrayList<Task> tasks;

    /**
     * Initializes tasks list.
     */
    public TaskList() {
        tasks = new ArrayList<>();
    }

    /**
     * Initialize TaskList with an array of tasks.
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
    public String print() {
        if (tasks.isEmpty()) {
            return "You have no tasks added yet :(\n";
        }

        StringBuilder output = new StringBuilder();
        for (int i = 0; i < tasks.size(); i++) {
            output.append((i + 1)).append(". ").append(tasks.get(i)).append("\n");
        }
        return output.toString();

    }

    /**
     * Prints out the list of tasks.
     *
     * @param stringToSearch String to search with.
     */
    public String print(String... stringToSearch) {
        if (tasks.isEmpty()) {
            return "You have no tasks added yet :(\n";
        }

        StringBuilder output = new StringBuilder();
        boolean isFound = false;

        // TODO: Fix arrowhead coding
        for (int i = 0; i < tasks.size(); i++) {
            for (String s : stringToSearch) {
                if (tasks.get(i).getTask().contains(s)) {
                    output.append((i + 1)).append(". ").append(tasks.get(i)).append("\n");
                    isFound = true;
                }
            }
        }

        if (!isFound) {
            return "No tasks found with given string(s).\n";
        } else {
            return output.toString();
        }
    }

    /**
     * Prints number of tasks.
     */
    public String printSize() {
        return "Now you have " + tasks.size() + " tasks in the list.\n";
    }

    /**
     * Marks task at index i.
     *
     * @param i Index of tasks to be marked.
     */
    public void mark(int i) {
        tasks.get(i).mark();
    }

    /**
     * Unmarks task at index i.
     *
     * @param i Index of tasks to be unmarked.
     */
    public void unmark(int i) {
        tasks.get(i).unmark();
    }

    public ArrayList<Task> getTasks() {
        return tasks;
    }
}
