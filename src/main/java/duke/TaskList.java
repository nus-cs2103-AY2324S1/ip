package duke;

import java.util.ArrayList;

import duke.task.Task;

/**
 * The Storage class stores the tasks and performs
 * interactions with the tasks
 *
 * @author Zi Xiang
 * @version CS2103 AY23/24 Sem 1
 */
public class TaskList {
    // Initialising variables and objects
    private ArrayList<Task> storage;

    public TaskList() {
        this.storage = new ArrayList<>();
    }

    public TaskList(ArrayList<Task> arrayList) {
        this.storage = arrayList;
    }

    /**
     * Adds a task to the storage
     * @param t the task to be added
     */
    public void add(Task t) {
        this.storage.add(t);
    }

    /** Displays Items in TaskList */
    public void display() {
        System.out.println("Here are the tasks in your list:");
        for (int i = 0; i < this.storage.size(); i++) {
            System.out.println((i + 1) + ". " + this.storage.get(i));
        }
    }

    /**
     * Marks a specific task as done
     * @param index the index of the task to be marked
     */
    public void mark(int index) {
        this.storage.get(index - 1).mark();
        System.out.println(this.storage.get(index - 1));
    }

    /**
     * Marks a specific task as undone
     * @param index the index of the task to be unmarked
     */
    public void unmark(int index) {
        this.storage.get(index - 1).unmark();
        System.out.println(this.storage.get(index - 1));
    }

    /**
     * Removes a specific task from storage
     * @param index the index of the task to be removed
     */
    public Task delete(int index) {
        return (this.storage.remove(index - 1));
    }

    public ArrayList<Task> getTasks() {
        return this.storage;
    }

    public int getSize() {
        return this.storage.size();
    }

    /**
     * Finds the tasks that contain the text.
     *
     * @param text the word to find.
     * @return an ArrayList of tasks.
     */
    public ArrayList<Task> find(String text) {
        ArrayList<Task> found = new ArrayList<>();
        for (int i = 0; i < this.getSize(); i++) {
            if (this.storage.get(i).getDescription().contains(text)) {
                found.add(this.storage.get(i));
            }
        }
        return found;
    }
}
