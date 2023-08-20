import java.util.ArrayList;

/**
 * The Storage class contains a fixed sized array that
 * stores the input from the parser class and can also
 * display the stored values to the user
 *
 * @author Zi Xiang
 * @version CS2103 AY23/24 Sem 1
 */
public class Storage {
    // Initialising variables and objects
    ArrayList<Task> storage;
    UI ui;

    public Storage() {
        this.storage = new ArrayList<Task>();
        this.ui = new UI();
    }

    /**
     * Method that adds a task to the storage
     * @param t The taskk to be added
     */
    public void add(Task t) {
        ui.line();
        System.out.println("Got it, will add task...");
        this.storage.add(t);
        System.out.println(t);
        System.out.println("Now, you have " + this.storage.size() + " task(s)");
        ui.line();
    }

    /** Display Items in storage */
    public void display() {
        ui.line();
        System.out.println("Here are the tasks in your list:");
        for (int i = 0; i < this.storage.size(); i++) {
            System.out.println((i + 1) + ". " + this.storage.get(i));
        }
        ui.line();
    }

    /**
     * Marks a specific task as done
     * @param index the index of the task to be marked
     */
    public void mark(int index) {
        ui.line();
        this.storage.get(index - 1).mark();
        System.out.println("Alright, it has been marked");
        System.out.println(this.storage.get(index - 1));
        ui.line();
    }

    /**
     * Marks a specific task as undone
     * @param index the index of the task to be unmarked
     */
    public void unmark(int index) {
        ui.line();
        this.storage.get(index - 1).unmark();
        System.out.println("Alright, it has been unmarked");
        System.out.println(this.storage.get(index - 1));
        ui.line();
    }

    /**
     * Method that removes a specific task from storage
     * @param index the index of the task to be removed
     */
    public void delete(int index) {
        ui.line();
        Task t = this.storage.remove(index - 1);
        System.out.println("Sigh... fine, removing...");
        System.out.println(t);
        System.out.println("Now, you have " + this.storage.size() + " task(s)");
        ui.line();
    }
}
