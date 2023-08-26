import tasks.*;
import java.util.ArrayList;

public class Database {
    private final static ArrayList<Task> db = new ArrayList<>();
    private static int total = 0; 

    public static void addTask(Task task) {
        db.add(task);
        total++;

        String resp = "I have added this task for you!\n" + task;
        resp += total > 1
                ? String.format("\nNow you have %d tasks in your list!", total)
                : String.format("\nNow you have %d task in your list!", total);
        UI.printWithFormat(resp);
    }


    public static void displayData() {
        String resp = "";
        if (total == 0) {
            resp = "You have no task right now:) Happy happy!";
        } else {
            for (int i = 0; i < total; i++) {
                if (i == total - 1) {
                    resp += String.format("%d.%s", i + 1, db.get(i));
                } else {
                    resp += String.format("%d.%s\n", i + 1, db.get(i));
                }
            }
        }
        UI.printWithFormat(resp);
    }


    public static void markAsDone(int index) {
        // check for range of index
        if (index >= total) {
            String errMsg = String.format("Sorry you do not have task #%d, " +
                    "try \"list\" to check your current list of tasks!", index + 1);
            throw new IndexOutOfBoundsException(errMsg);
        }

        db.get(index).markAsDone();

        String resp = "Good job! I've marked this task as done:\n" + db.get(index);
        UI.printWithFormat(resp);
    }

    public static void markAsNotDone(int index) {
        // check for range of index
        if (index >= total) {
            String errMsg = String.format("Sorry you do not have task #%d, " +
                    "try \"list\" to check your current list of tasks!", index + 1);
            throw new IndexOutOfBoundsException(errMsg);
        }

        db.get(index).markAsNotDone();

        String resp = "Okie dokie! I've unmarked it for you:\n" + db.get(index);
        UI.printWithFormat(resp);
    }

    public static void delete(int index) {
        // check for range of index
        if (index >= total) {
            String errMsg = String.format("Sorry you do not have task #%d, " +
                    "try \"list\" to check your current list of tasks!", index + 1);
            throw new IndexOutOfBoundsException(errMsg);
        }

        Task t = db.get(index);
        db.remove(index);
        total--;

        String resp = "Noted. I've removed this task:\n" +
                        t +
                        String.format("\nNow you have %d tasks in your list!", total);
        UI.printWithFormat(resp);
    }
}
