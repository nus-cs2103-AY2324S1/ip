package duke.main;

import java.util.ArrayList;
import java.util.Arrays;

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
        assert task == tasks.get(tasks.size() - 1);
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
     * Adds a new tag to task.
     *
     * @param index Index of task.
     * @param tag Tag to be added.
     */
    public void addTag(int index, String tag) {
        tasks.get(index).addTag(tag);
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
     * Prints out the list of tasks using a search string.
     *
     * @param stringToSearch String to search with.
     */
    public String print(String... stringToSearch) {
        if (tasks.isEmpty()) {
            return "You have no tasks added yet :(\n";
        }

        StringBuilder output = new StringBuilder();

        tasks.stream()
                .filter(task -> Arrays.stream(stringToSearch).anyMatch(s -> task.getTask().contains(s)))
                .forEachOrdered(task -> output.append((tasks.indexOf(task) + 1))
                        .append(". ").append(task).append("\n"));

        if (output.length() == 0) {
            return "No tasks found with given string(s).\n";
        } else {
            return output.toString();
        }
    }

    /**
     * Prints tags of task at specified index.
     *
     * @param index Index of task.
     * @return List of tags for specified task.
     */
    public String printTags(int index) {
        if (tasks.isEmpty()) {
            return "You have no tasks added yet :(\n";
        }

        StringBuilder output = new StringBuilder();
        Task task = tasks.get(index);
        String[] taskTags = task.getTags();

        String tempString = "Tags for " + task.getTask() + ": \n";
        output.append(tempString);
        for (String tag : taskTags) {
            tempString = "#" + tag + "\n";
            output.append(tempString);
        }

        return output.toString();
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
