import tasks.*;
import java.util.ArrayList;

public class Database {
    private final ArrayList<Task> db = new ArrayList<>();
    private int total = 0;  // total also indicates the first free slot

    public void addTask(Task task) {
        this.db.add(task);
        this.total++;

        UI.displayCheems();
        System.out.print("I have added this task for you!\n" + task);
        System.out.printf("\nNow you have %d tasks in your list!\n", this.total);
    }


    public void displayData() {
        UI.displayCheems();
        if (this.total == 0) {
            System.out.println("You have no task right now:) Happy happy!");
        } else {
            for (int i = 0; i < this.total; i++) {
                System.out.printf("%d.%s\n", i + 1, this.db.get(i));
            }
        }
    }


    public void markAsDone(int index) {
        // check for range of index
        if (index >= this.total) {
            throw new IndexOutOfBoundsException(String.format("Sorry you do not have task #%d, try \"list\" to check your current list of tasks!", index + 1));
        }

        this.db.get(index).markAsDone();

        UI.displayCheems();
        System.out.println("Good job! I've marked this task as done:");
        System.out.println(this.db.get(index));
    }

    public void markAsNotDone(int index) {
        // check for range of index
        if (index >= this.total) {
            throw new IndexOutOfBoundsException(String.format("Sorry you do not have task #%d, try \"list\" to check your current list of tasks!", index + 1));
        }

        this.db.get(index).markAsNotDone();

        UI.displayCheems();
        System.out.println("Okie dokie! I've unmarked it for you:");
        System.out.println(this.db.get(index));
    }
}
