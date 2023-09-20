package mainDuke;

import java.util.ArrayList;

import mainDuke.exceptions.DukeException;
import mainDuke.task.Task;

/**
 * Contains the list of tasks, and provides methods to edit the list and interact with storage/hard drive.
 */
public class TaskList {
    /**
     * ArrayList of tasks.
     */
    private static ArrayList<Task> tasks = new ArrayList<>();

    /**
     * Saves current tasks to hard drive.
     */
    public static void saveList() {
        Storage.saveTask(tasks);
    }

    /**
     * Loads tasks from hard drive into tasks, prints a message if there
     * are no tasks found.
     */
    public static String updateFromStorage() {
        try {
            tasks = Storage.loadTasks();
        } catch (DukeException e) {
            return ("No tasks found in storage, starting new list\n");
        }
        return "";
    }

    /**
     * Prints the list of task in their string format,
     * each task starts on a new line.
     * @throws DukeException if there are no tasks in the list.
     */
    public static String getListAsString() throws DukeException {
        if (tasks.size() == 0) {
            throw new DukeException("There are no tasks yet");
        }
        StringBuilder stringBuilder = new StringBuilder();
        for (int i = 0; i < tasks.size(); i++) {
            String str = (i + 1) + "." + tasks.get(i).toString();
            stringBuilder.append(str).append("\n");
        }
        return stringBuilder.toString();
    }

    /**
     * Add a task to the current list.
     * @param task task to be added.
     */
    public static String addTask(Task task) {
        tasks.add(task);
        return ("Got it. I've added this task: \n" + task + "\nnow you have "
                + tasks.size() + " tasks in the list");
    }

    /**
     * Deletes a task from the list.
     * @param i index of task which is to be deleted.
     * @throws DukeException if index is out of range or there are no tasks to delete.
     */
    public static String deleteTask(int i) throws DukeException {
        if (tasks.size() <= 0) {
            throw new DukeException("There are no tasks to delete");
        } else if (tasks.size() < i) {
            throw new DukeException("Task.Task index out of range");
        }
        Task deleted = tasks.remove(i);
        return ("Noted. I've removed this task:\n" + deleted + "\nNow you have "
                + tasks.size() + " tasks in the list");
    }

    /**
     * Marks a task at an index as done.
     * @param i index of task to be marked as done.
     * @throws DukeException if task is already done.
     */
    public static String markTask(int i) throws DukeException {
        return tasks.get(i).mark();
    }

    /**
     * unmarks task at an index as undone
     * @param i index of task which is to be marked undone
     * @throws DukeException if task is still undone
     */
    public static String unmarkTask(int i) throws DukeException {
        return tasks.get(i).unmark();
    }

    /**
     * getter for task from list
     * @param i index of task to be gotten
     * @return task at index i
     */
    public static Task getTask(int i) {
        return tasks.get(i);
    }

    /**
     * find tasks containing a certain word
     * @param word word to look for
     * @return ArrayList of Tasks containing the word
     */
    public static ArrayList<Task> find(String word) {
        String wordToFind = word.split(" ")[1];
        ArrayList<Task> result = new ArrayList<>();
        for (Task task : tasks) {
            if (task.getDescription().contains(wordToFind)) {
                result.add(task);
            }
        }
        return result;
    }
}
