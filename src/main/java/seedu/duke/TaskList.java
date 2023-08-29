package seedu.duke;

import java.util.ArrayList;
import java.util.List;

/**
 * Represents a list of tasks input by users.
 *
 * @author KAM JIA YUE
 * @since 2023-08-29
 */
public class TaskList {
    private List<Task> list;
    private Storage storage;

    /**
     * The main constructor for the TaskList class.
     */
    public TaskList() {
        this.list = new ArrayList<>();
        this.storage = new Storage();
    }

    /**
     * Sets the path of the storage file.
     *
     * @param filePath Path of the storage file.
     */
    public void setHardDiskFilePath(String filePath) {
        this.storage.setHardDiskFilePath(filePath);
    };

    /**
     * Loads data to the task list.
     */
    public void loadData() {
        this.storage.loadData(this.list);
    }

    /**
     * Lists out all the tasks in task list.
     */
    public void listOutEverything() {
        System.out.println(Ui.i5 + "Here are the tasks in your list:");
        for (int i = 0; i < this.list.size(); i++) {
            System.out.println(Ui.i5 + (i + 1) + "." + this.list.get(i));
        }
    }

    /**
     * Checks if the given index is an invalid index
     * for operations on the task list.
     *
     * @param index Index of a task in the task list.
     * @return false if the index is valid, true otherwise.
     */
    public boolean isOutOfRange(int index) {
        return index < 0 || this.list.size() <= index;
    }

    /**
     * Marks the task as completed and
     * updates the storage file.
     *
     * @param index Index of a task in the task list.
     */
    public void mark(int index) {
        this.list.get(index).mark();
        this.storage.updateHardDisk(this.list);
    }

    /**
     * Marks the task as not completed and
     * updates the storage file.
     *
     * @param index Index of a task in the task list.
     */
    public void unmark(int index) {
        this.list.get(index).unmark();
        this.storage.updateHardDisk(this.list);
    }

    /**
     * Adds the given task to the task list.
     *
     * @param task Given task object.
     */
    public void add(Task task) {
        this.list.add(task);
        System.out.println(Ui.i5 + "Got it. I've added this task:");
        System.out.println(Ui.i7 + task);
        System.out.println(Ui.i5 + "Now you have " + this.list.size() + " tasks in the list.");
        this.storage.updateHardDisk(this.list);
    }

    /**
     * Removes the given task from the task list.
     *
     * @param index Index of the task to be removed.
     */
    public void remove(int index) {
        Task t = this.list.remove(index);
        System.out.println(Ui.i5 + "Noted. I've removed this task:");
        System.out.println(Ui.i7 + t);
        System.out.println(Ui.i5 + "Now you have " + this.list.size() + " tasks in the list.");
        this.storage.updateHardDisk(this.list);
    }
}
