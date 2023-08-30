import java.util.ArrayList;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.FileNotFoundException;
import java.util.Scanner;


/**
 * The Storage class is responsible for storing and managing the tasks in the task list.
 */
public class Storage {
    private static final ArrayList<Task> list = new ArrayList<>();

    /**
     * Adds a task to the task list.
     *
     * @param task The task to be added.
     */
    public static void addToTask(Task task) {
        list.add(task);
        System.out.println("Got it. I've added this task:\n    " + task.toString());
        System.out.println("Now you have " + list.size() + " tasks in the list.");
    }

    /**
     * Displays the list of tasks.
     */
    public static void displayList() {
        System.out.println("Here are the tasks in your list:");
        for (int i = 0; i < list.size(); i++) {
            System.out.println((i + 1) + "." + list.get(i).toString());
        }
    }

    /**
     * Marks a task as done.
     *
     * @param index The index of the task to be marked as done.
     * @throws IndexOutOfBoundsException If the index is out of range.
     */
    public static void mark(int index) {
        if (index < 1 || index > list.size()) {
            throw new IndexOutOfBoundsException("Task index is out of range. " +
                    "Check the number of tasks using the 'list' command.");
        }
        Task taskToEdit = list.get(index - 1);
        taskToEdit.markAsDone();
        System.out.println("Nice! I've marked this task as done:\n" + taskToEdit);
    }

    /**
     * Marks a task as not done.
     *
     * @param index The index of the task to be marked as not done.
     * @throws IndexOutOfBoundsException If the index is out of range.
     */
    public static void unMark(int index) {
        if (index < 1 || index > list.size()) {
            throw new IndexOutOfBoundsException("Task index is out of range. " +
                    "Check the number of tasks using the 'list' command.");
        }
        Task taskToEdit = list.get(index - 1);
        taskToEdit.unMark();
        System.out.println("OK, I've marked this task as not done yet:\n" + taskToEdit);
    }

    /**
     * Deletes a task from the task list.
     *
     * @param index The index of the task to be deleted.
     * @throws IndexOutOfBoundsException If the index is out of range.
     */
    public static void delete(int index) {
        if (index < 1 || index > list.size()) {
            throw new IndexOutOfBoundsException("Task index is out of range. " +
                    "Check the number of tasks using the 'list' command.");
        }
        Task taskToEdit = list.get(index - 1);
        list.remove(index - 1);
        System.out.println("Noted. I've removed this task:\n" + taskToEdit);
        System.out.println("Now you have " + list.size() + " tasks in the list.");
    }
}
