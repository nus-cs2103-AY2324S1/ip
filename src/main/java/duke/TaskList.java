package duke;

import duke.task.Task;

import java.util.ArrayList;

/**
 * The duke.Storage class stores the tasks and performs
 * interactions with the tasks
 *
 * @author Zi Xiang
 * @version CS2103 AY23/24 Sem 1
 */
public class TaskList {
    // Initialising variables and objects
    ArrayList<Task> storage;
    UI ui;

    public TaskList() {
        this.storage = new ArrayList<>();
        this.ui = new UI();
    }

    public TaskList(ArrayList arrayList) {
        this.storage = arrayList;
        this.ui = new UI();
    }

    /**
     * Adds a task to the storage
     * @param t The task to be added
     */
    public void add(Task t) {
        this.storage.add(t);
    }

    /** Displays Items in duke.TaskList */
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
        Task t = this.storage.remove(index - 1);
        return t;
    }

    public ArrayList getTasks() {
        return this.storage;
    }

    public int getSize() {
        return this.storage.size();
    }

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
