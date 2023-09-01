package duke.components;

import duke.tasks.Task;

import java.util.ArrayList;

/**
 * Class for the list of tasks.
 */
public class TaskList {
    /**
     * tasks contains a list of tasks.
     */
    private static ArrayList<Task> tasks;

    /**
     * Stores the task list loaded from the save file.
     *
     * @param tasks List of task loaded from storage.
     */
    public TaskList(ArrayList<Task> tasks) {
        this.tasks = tasks;
    }

    /**
     * Add a new task to list.
     *
     * @param task Task to be added to the list.
     */
    public void addTask(Task task) {
        tasks.add(task);
    }

    /**
     * Mark a specific task as completed.
     *
     * @param index Index of task completed.
     * @throws DukeException Indexing error.
     */
    public static void mark(int index) throws DukeException {
        if (isValidIndex(index)) {
            tasks.get(index).setDone();
        } else { // Index error
            throw new DukeException("I'm afraid the task does not exist. " +
                    "Perhaps you might want to see your list again?");
        }
    }

    /**
     * Mark a specific task as incomplete.
     *
     * @param index Index of task to unmark.
     * @throws DukeException Indexing error.
     */
    public static void unmark(int index) throws DukeException{
        if (isValidIndex(index)) {
            tasks.get(index).setNotDone();
        } else { // Index error
            throw new DukeException("I'm afraid the task does not exist. " +
                    "Perhaps you might want to see your list again?");
        }
    }

    /**
     * Delete a specific task.
     *
     * @param index Index of task to delete.
     * @throws DukeException Indexing error.
     */
    public void delete(int index) throws DukeException {
        if (isValidIndex(index)) {
            tasks.remove(index);
        } else { // Index error
        throw new DukeException("I'm afraid the task does not exist. " +
                "Perhaps you might want to see your list again?");
        }
    }

    public static ArrayList<Task> getTasks() {
        return tasks;
    }

    public static Task getTask(int index) {
        return tasks.get(index);
    }

    public static int getSize() {
        return tasks.size();
    }

    /**
     * Iterates through tasks and return the String representation for each task.
     *
     * @return String representation of task list.
     */
    public static String list() {
        String result = "Here are the tasks in your list:\n";
        for (int i = 0; i < tasks.size(); i++) { // Generates the String representation of the list
            result += i + 1 + ". " + tasks.get(i) + "\n";
        }
        if (result != "Here are the tasks in your list:\n") {
            return result;
        } else { // Empty list
            return "There is nothing on your list currently. " +
                    "Perhaps you might want to add a new task?";
        }
    }

    /**
     * Checks if index is out of bounds.
     *
     * @param index Index to be checked.
     * @return Boolean of whether index is out of bounds.
     */
    private static boolean isValidIndex(int index) {
        return index >= 0 && index < tasks.size();
    }

    /**
     * Filters the list of task based on a given keyword. A new list will be returned for task containing the keyword.
     * @param keyword The task list will be filtered against this keyword.
     * @return A list of tasks that contains the keyword.
     */
    public String filter(String keyword) {
        String result = "Here are the tasks in your list containing " + keyword + ":\n";
        int counter = 0;
        for (Task task : tasks) { // Generates the String representation of the list
            String description = task.getDescription();
            if (description.contains(keyword)) {
                counter += 1;
                result += counter + ". " + task + "\n";
            }
        }
        if (counter > 0) {
            return result;
        } else { // Empty list
            return "There is nothing on your list currently that matches the keyword \"" + keyword + "\". " +
                    "Perhaps you might want to add a new task or try a different keyword?";
        }
    }

}
