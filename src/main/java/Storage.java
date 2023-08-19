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
    Task[] storage;
    int index;
    UI ui;

    public Storage() {
        this.storage = new Task[100];
        this.index = 0;
        this.ui = new UI();
    }

    /** Add item to the storage */
    public void add(String item) {
        ui.line();
        if (index >= 100){
            System.out.println("Storage at maximum capacity");
        } else {
            Task t = new Task(item);
            storage[index] = t;
            index += 1;
            System.out.println("added: " + item);
        }
        ui.line();
    }

    /** Display Items in storage */
    public void display() {
        ui.line();
        System.out.println("Here are the tasks in your list:");
        for (int i = 0; i < index; i++) {
            System.out.println((i + 1) + ". " + storage[i]);
        }
        ui.line();
    }

    /**
     * Marks a specific task as done
     * @param index the index of the task to be marked
     */
    public void mark(int index) {
        ui.line();
        storage[index - 1].mark();
        System.out.println("Alright, it has been marked");
        System.out.println(storage[index - 1]);
        ui.line();
    }

    /**
     * Marks a specific task as undone
     * @param index the index of the task to be unmarked
     */
    public void unmark(int index) {
        ui.line();
        storage[index - 1].unmark();
        System.out.println("Alright, it has been unmarked");
        System.out.println(storage[index - 1]);
        ui.line();
    }
}
