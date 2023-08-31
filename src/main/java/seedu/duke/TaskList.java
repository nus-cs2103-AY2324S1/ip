package seedu.duke;

import java.util.ArrayList;

/**
 * Encapsulates the TaskList class.
 * A TaskList represents the user's accumulated tasks.
 */
public class TaskList {
    private ArrayList<Task> tasks;

    public TaskList() {
        this.tasks = new ArrayList<>();
    }

    public TaskList(ArrayList<Task> tasks) {
        this.tasks = tasks;
    }

    /**
     * Obtains the accumulated tasks.
     *
     * @return An ArrayList<Task> containing the current tasks
     */
    public ArrayList<Task> getTasks() {
        return this.tasks;
    }

    /**
     * Appends the new task at the end of the current ArrayList<Task> and saves it.
     *
     * @param task The task to be appended.
     * @return The ArrayList<Task> containing the updated tasks.
     */
    // Adds the Task argument at the end of the list
    public ArrayList<Task> addTask(Task task) {
        tasks.add(task);
        new Storage("./data/duke.txt").save(tasks);
        return tasks;
    }

    /**
     * Reads the duke.txt file, unserializes it and obtain the arraylist representing saved tasks.
     * Depending on the purpose, information in the arraylist is updated, and the whole arraylist is
     * serialized and saved again in the duke.txt file.
     *
     * @param index The task number specified.
     * @param purpose The type of update: mark or delete.
     * @return The task which has been marked/deleted.
     * @throws InvalidDataFormatException if the data in the duke.txt file is not in the correct format.
     */
    //
    public Task markOrDeleteTask(int index, String purpose) throws InvalidDataFormatException {
        if (purpose.equals("mark")) {
            this.tasks.get(index).markTask();
            new Storage("./data/duke.txt").save(this.tasks);
            return this.tasks.get(index);
        } else if (purpose.equals("unmark")) {
            this.tasks.get(index).unMarkTask();
            new Storage("./data/duke.txt").save(this.tasks);
            return this.tasks.get(index);
        } else {
            Task deletedTask = this.tasks.get(index);
            this.tasks.remove(index);
            new Storage("./data/duke.txt").save(this.tasks);
            return deletedTask;
        }
    }

    /**
     * Finds tasks in the accumulated tasks that contain the keyword specified.
     *
     * @param keyword The string to be matched with the accumulated tasks.
     * @return An ArrayList<Task> containing the tasks that contain the keyword.
     */
    public ArrayList<Task> find(String keyword) {
        ArrayList<Task> results = new ArrayList<>();

        for (int i = 0; i < tasks.size(); i++) {
            if (tasks.get(i).name.contains(keyword)) {
                results.add(tasks.get(i));
            }
        }
        return results;
    }
}
