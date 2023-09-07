package monday.task;

import java.io.IOException;
import java.util.ArrayList;

import monday.monday.ui.Ui;
import monday.storage.Storage;

/**
 * The TaskList class is responsible for storing and managing the tasks in the task list.
 */
public class TaskList {
    private ArrayList<Task> list;
    private Storage storage;

    /**
     * Constructs a TaskList object with the specified file path.
     *
     * @param filePath the file path to store the tasks
     */
    public TaskList(String filePath) {
        this.storage = new Storage(filePath);
        try {
            this.list = storage.load();
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
            storage.save(list);
        } catch (IOException e) {
            System.out.println("Error saving tasks: " + e.getMessage());
        }
    }

    /**
     * Adds a task to the task list.
     *
     * @param task The task to be added.
     * @return Message with the task added.
     */
    public String addToTask(Task task) {
        list.add(task);
        save();
        return Ui.addTask(task, list.size());
    }

    /**
     * Displays the list of tasks.
     *
     * @return Message with the list of all tasks.
     */
    public String displayList() {
        StringBuilder ans = new StringBuilder("Here are the tasks in your list:\n");
        for (int i = 0; i < list.size(); i++) {
            ans.append((i + 1)).append(".").append(list.get(i).toString()).append("\n");
        }
        return ans.toString();
    }

    /**
     * Marks a task as done.
     *
     * @param index The index of the task to be marked as done.
     * @return Message after marking the task as done.
     * @throws IndexOutOfBoundsException If the index is out of range.
     */
    public String mark(int index) {
        if (index < 1 || index > list.size()) {
            throw new IndexOutOfBoundsException("Task index is out of range. "
                    + "Check the number of tasks using the 'list' command.");
        }
        Task taskToEdit = list.get(index - 1);
        taskToEdit.markAsDone();
        save();
        return Ui.markTask(taskToEdit);
    }

    /**
     * Marks a task as not done.
     *
     * @param index The index of the task to be marked as not done.
     * @return Message after marking the task as not done.
     * @throws IndexOutOfBoundsException If the index is out of range.
     */
    public String unMark(int index) {
        if (index < 1 || index > list.size()) {
            throw new IndexOutOfBoundsException("Task index is out of range. "
                    + "Check the number of tasks using the 'list' command.");
        }
        Task taskToEdit = list.get(index - 1);
        taskToEdit.unMark();
        save();
        return Ui.unMarkTask(taskToEdit);
    }

    /**
     * Deletes a task from the task list.
     *
     * @param index The index of the task to be deleted.
     * @return Message after deleting the task.
     * @throws IndexOutOfBoundsException If the index is out of range.
     */
    public String delete(int index) {
        if (index < 1 || index > list.size()) {
            throw new IndexOutOfBoundsException("Task index is out of range. "
                    + "Check the number of tasks using the 'list' command.");
        }
        Task taskToEdit = list.get(index - 1);
        list.remove(index - 1);
        save();
        return Ui.deleteTask(taskToEdit, list.size());
    }

    /**
     * Finds and prints tasks containing the specified keyword.
     *
     * @param keyword the keyword to search for in the tasks
     * @return Message with the list of tasks containing the keyword.
     */
    public String find(String keyword) {
        if (list.isEmpty()) {
            return ("Your list is empty.");
        } else {
            int matchingTaskCount = 1;
            StringBuilder ans = new StringBuilder("Here are the matching tasks in your list:\n");
            for (Task curr : list) {
                if (curr.toString().contains(keyword)) {
                    ans.append(matchingTaskCount).append(".").append(curr).append("\n");
                    matchingTaskCount++;
                }
            }
            return ans.toString();
        }
    }
}
