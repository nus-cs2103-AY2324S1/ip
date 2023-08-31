package monday.task;

import java.io.IOException;
import java.util.ArrayList;

import monday.storage.Storage;

/**
 * The TaskList class is responsible for storing and managing the tasks in the task list.
 */
public class TaskList {
    private ArrayList<Task> lists;
    private Storage storage;

    /** 
     * Constructs a TaskList object with the specified file path. 
     * 
     * @param filePath the file path to store the tasks 
     */ 
    public TaskList(String filePath) {
        this.storage = new Storage(filePath);
        try {
            this.lists = storage.load();
        } catch (IOException | ClassNotFoundException e) {
            System.out.println("Error loading tasks from file: " + e.getMessage());
        }
    }

    /**
    * Saves the list of tasks to the storage.
    * 
    * @throws IOException if an I/O error occurs while saving the tasks
    */
    private void save() {
        try {
            storage.save(lists);
        } catch (IOException e) {
            System.out.println("Error saving tasks: " + e.getMessage());
        }
    }
    
    /**
     * Adds a task to the task list.
     *
     * @param task The task to be added.
     */
    public void addToTask(Task task) {
        lists.add(task);
        save();
        System.out.println("Got it. I've added this task:\n    " + task.toString());
        System.out.println("Now you have " + lists.size() + " tasks in the list.");
    }

    /**
     * Displays the list of tasks.
     */
    public void displayList() {
        System.out.println("Here are the tasks in your list:");
        for (int i = 0; i < lists.size(); i++) {
            System.out.println((i + 1) + "." + lists.get(i).toString());
        }
    }

    /**
     * Marks a task as done.
     *
     * @param index The index of the task to be marked as done.
     * @throws IndexOutOfBoundsException If the index is out of range.
     */
    public void mark(int index) {
        if (index < 1 || index > lists.size()) {
            throw new IndexOutOfBoundsException("Task index is out of range. " +
                    "Check the number of tasks using the 'list' command.");
        }
        Task taskToEdit = lists.get(index - 1);
        taskToEdit.markAsDone();
        save();
        System.out.println("Nice! I've marked this task as done:\n" + taskToEdit);
    }

    /**
     * Marks a task as not done.
     *
     * @param index The index of the task to be marked as not done.
     * @throws IndexOutOfBoundsException If the index is out of range.
     */
    public void unMark(int index) {
        if (index < 1 || index > lists.size()) {
            throw new IndexOutOfBoundsException("Task index is out of range. " +
                    "Check the number of tasks using the 'list' command.");
        }
        Task taskToEdit = lists.get(index - 1);
        taskToEdit.unMark();
        save();
        System.out.println("OK, I've marked this task as not done yet:\n" + taskToEdit);
    }

    /**
     * Deletes a task from the task list.
     *
     * @param index The index of the task to be deleted.
     * @throws IndexOutOfBoundsException If the index is out of range.
     */
    public void delete(int index) {
        if (index < 1 || index > lists.size()) {
            throw new IndexOutOfBoundsException("Task index is out of range. " +
                    "Check the number of tasks using the 'list' command.");
        }
        Task taskToEdit = lists.get(index - 1);
        lists.remove(index - 1);
        save();
        System.out.println("Noted. I've removed this task:\n" + taskToEdit);
        System.out.println("Now you have " + lists.size() + " tasks in the list.");
    }

    /**
     * Finds and prints tasks containing the specified keyword.
     *
     * @param keyword the keyword to search for in the tasks
     */
    public void find(String keyword) {
        if (list.isEmpty()) {
            System.out.println("Your list is empty.");
        } else {
            int matchingTaskCount = 1;
            for (Task curr : list) {
                if (curr.toString().contains(keyword)) {
                    System.out.println(matchingTaskCount + "." + curr);
                    matchingTaskCount++;
                }
            }
        }
    }
}
