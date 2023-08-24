import tasks.*;
import java.util.ArrayList;

public class Database {
    private final ArrayList<Task> db = new ArrayList<>();
    private int total = 0;  // total also indicates the first free slot

    public void addTask(Task task) {
        this.db.add(task);
        this.total++;

        String resp = "I have added this task for you!\n" +
                      task +
                      String.format("\nNow you have %d tasks in your list!", this.total);
        UI.printWithFormat(resp);
    }


    public void displayData() {
        String resp = "";
        if (this.total == 0) {
            resp = "You have no task right now:) Happy happy!";
        } else {
            for (int i = 0; i < this.total; i++) {
                if (i == this.total - 1) {
                    resp += String.format("%d.%s", i + 1, this.db.get(i));
                } else {
                    resp += String.format("%d.%s\n", i + 1, this.db.get(i));
                }
            }
        }
        UI.printWithFormat(resp);
    }


    public void markAsDone(int index) {
        // check for range of index
        if (index >= this.total) {
            String errMsg = String.format("Sorry you do not have task #%d, " +
                    "try \"list\" to check your current list of tasks!", index + 1);
            throw new IndexOutOfBoundsException(errMsg);
        }

        this.db.get(index).markAsDone();

        String resp = "Good job! I've marked this task as done:\n" + this.db.get(index);
        UI.printWithFormat(resp);
    }

    public void markAsNotDone(int index) {
        // check for range of index
        if (index >= this.total) {
            String errMsg = String.format("Sorry you do not have task #%d, " +
                    "try \"list\" to check your current list of tasks!", index + 1);
            throw new IndexOutOfBoundsException(errMsg);
        }

        this.db.get(index).markAsNotDone();

        String resp = "Okie dokie! I've unmarked it for you:\n" + this.db.get(index);
        UI.printWithFormat(resp);
    }

    public void delete(int index) {
        // check for range of index
        if (index >= this.total) {
            String errMsg = String.format("Sorry you do not have task #%d, " +
                    "try \"list\" to check your current list of tasks!", index + 1);
            throw new IndexOutOfBoundsException(errMsg);
        }

        Task t = this.db.get(index);
        this.db.remove(index);
        this.total--;

        String resp = "Noted. I've removed this task:\n" +
                        t +
                        String.format("\nNow you have %d tasks in your list!", this.total);
        UI.printWithFormat(resp);
    }
}
