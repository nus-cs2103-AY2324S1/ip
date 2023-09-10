package roo;

import java.time.LocalDate;
import java.util.ArrayList;

import roo.task.Task;

/**
 * Manages a list of tasks and interacts with the Storage class for task-related operations.
 */
public class TaskList {
    private final ArrayList<Task> data = new ArrayList<>();
    private final Storage storage;

    /**
     * Constructs a TaskList with a reference to the associated Storage.
     * @param storage The Storage instance associated with this TaskList.
     */
    public TaskList(Storage storage) {
        this.storage = storage;
    }

    /**
     * Checks if a task at a specified index is marked as done.
     * @param index The index of the task.
     * @return true if the task is marked as done, false otherwise.
     */
    public boolean isDone(int index) {
        return data.get(index).done();
    }

    /**
     * Returns the number of tasks in the list.
     * @return The number of tasks in the list.
     */
    public int size() {
        return this.data.size();
    }


    /**
     * Adds a task to the list without updating the storage immediately.
     * @param task The task to be added.
     */
    public void add(Task task) {
        this.data.add(task);
    }

    /**
     * Adds a new task to the list and updates the storage.
     * @param task The task to be added.
     */
    public void addNew(Task task) {
        this.data.add(task);
        this.storage.add(task);
    }

    /**
     * Clears the task list and updates the storage.
     */
    public void clear() {
        this.data.clear();
        this.storage.clear();
    }

    /**
     * Lists tasks matching the given date.
     * @param date The date for which tasks are to be listed.
     */
    public String listDateEvents(LocalDate date) {
        StringBuilder str = new StringBuilder();
        for (Task dt : data) {
            if (dt.getDate() != null && dt.getDate().getDayOfMonth() == date.getDayOfMonth()) {
                str.append("- ").append(dt.toString()).append("\n");
            }
        }
        return str.toString();
    }

    /**
     * Lists tasks matching a given keyword.
     * @param keyword The keyword to search for in tasks.
     */
    public String find(String keyword) {
        int count = 1;
        StringBuilder str = new StringBuilder();
        for (Task dt : data) {
            if (dt.toString().contains(keyword)) {
                System.out.println(count + ". " + dt.toString());
                str.append(count).append(". ").append(dt.toString()).append("\n");
                count++;
            }
        }
        return str.toString();
    }

    /**
     * Marks a task at a specific index as done and updates the storage.
     * @param index The index of the task to mark as done.
     */
    public void markDone(int index) {
        data.get(index).markDone();
        storage.modifyFile(this.data);
    }

    /**
     * Marks a task at a specific index as undone and updates the storage.
     * @param index The index of the task to mark as undone.
     */
    public void markUndone(int index) {
        data.get(index).markUndone();
        storage.modifyFile(this.data);
    }

    /**
     * Deletes a task at a specific index, updates the storage, and displays updated task count.
     * @param index The index of the task to delete.
     */
    public String delete(int index) {
        String temp = data.get(index).toString();
        data.remove(index);
        storage.modifyFile(this.data);
        return temp;
    }

    /**
     * Gets the string representation of a task at a specific index.
     * @param index The index of the task.
     * @return The string representation of the task.
     */
    public String taskDetails(int index) {
        return data.get(index).toString();
    }

    /**
     * Lists all tasks in the task list.
     */
    public String list() {
        if (data.isEmpty()) {
            return "Congrats!!! Nothing to do now!!!\n";
        } else {
            StringBuilder str = new StringBuilder();
            for (int i = 0; i < data.size(); i++) {
                Task dt = data.get(i);
                str.append((i + 1)).append(". ").append(dt.toString()).append("\n");
            }
            return str.toString();
        }
    }

}
