package tasklist;

import java.util.ArrayList;

import storage.DataReader;
import storage.Storage;
import tasks.Task;

/**
 * The TaskList class manages a list of tasks, providing
 * methods to add, mark, unmark, delete, and print tasks.
 */
public class TaskList {
    private static ArrayList<Task> ls = DataReader.readTasksFromFile();

    /**
     * Adds a task to the list and updates the storage file.
     *
     * @param task The task to be added.
     */
    public void add(Task task) {
        if (task == null) {
            return;
        }
        System.out.println("Got it. I've added this task:");
        ls.add(task);
        System.out.println(task);
        System.out.println("Now you have " + this.size() + " tasks in the list.");
    }

    /**
     * Marks a task as done and updates the storage file.
     *
     * @param pos The position of the task to be marked.
     */
    public void mark(int pos) {
        System.out.println("Nice! I've marked this task as done:");
        ls.get(pos - 1).toMark();
        System.out.println(ls.get(pos - 1).toString());
    }

    /**
     * Retrieves a task from the list at the specified index.
     *
     * @param i The index of the task to retrieve.
     * @return The task at the specified index.
     */
    public Task get(int i) {
        return ls.get(i);
    }

    /**
     * Unmarks a task as done and updates the storage file.
     *
     * @param pos The position of the task to be unmarked.
     */
    public void unmark(int pos) {
        System.out.println("OK, I've marked this task as not done yet:");
        ls.get(pos - 1).toUnmark();
        System.out.println(ls.get(pos - 1));
    }

    /**
     * Returns the number of tasks in the list.
     *
     * @return The number of tasks in the list.
     */
    public int size() {
        return ls.size();
    }

    /**
     * Deletes a task from the list and updates the storage file.
     *
     * @param pos The position of the task to be deleted.
     */
    public void delete(int pos) {
        System.out.println("Noted. I've removed this task:");
        System.out.println(" " + ls.get(pos - 1));
        ls.remove(pos - 1);
        Storage.deleteLine(pos);
        System.out.println("Now you have " + ls.size() + " tasks in the list.");
    }

    /**
     * Prints the list of tasks in a formatted manner.
     */
    public void print() {
        System.out.println("Here are the tasks in your list:");
        for (int i = 0; i < ls.size(); i++) {
            int order = i + 1;
            Task cur = ls.get(i);
            System.out.println(order + ". " + " " + cur);
        }
    }

    /**
     * Searches for tasks in the task list that contain the
     * specified keyword and prints the matching tasks.
     *
     * @param keyword The keyword to search for in task descriptions.
     */
    public void find(String keyword) {
        System.out.println("Here are the matching tasks in your list:");
        int counter = 0;
        for (int i = 0; i < ls.size(); i++) {
            Task task = ls.get(i);
            if (task.containKey(keyword)) {
                counter++;
                System.out.println(counter + "." + task);
            }
        }
        if (counter == 0) {
            System.out.println("Sorry.No tasks match the keyword: " + keyword);
        }
    }
}
