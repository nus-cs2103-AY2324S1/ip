package duke;

import duke.task.Task;

import java.util.ArrayList;

/**
 * The duke.Storage class contains a fixed sized array that
 * stores the input from the parser class and can also
 * display the stored values to the user
 *
 * @author Zi Xiang
 * @version CS2103 AY23/24 Sem 1
 */
public class TaskList {
    // Initialising variables and objects
    ArrayList<Task> storage;
    UI ui;

    public TaskList() {
        this.storage = new ArrayList<Task>();
        this.ui = new UI();
    }

    public TaskList(ArrayList arrayList) {
        this.storage = arrayList;
        this.ui = new UI();
    }

    /**
     * Method that adds a task to the storage
     * @param t The task to be added
     */
    public void add(Task t) {
        this.storage.add(t);
    }

    /** Display Items in duke.TaskList */
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
     * Method that removes a specific task from storage
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
}
