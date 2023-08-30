package cheems;

import cheems.tasks.*;
import java.util.ArrayList;

public class Tasklist {
    private static final ArrayList<Task> list = new ArrayList<>();
    private static int total = 0;  // total also indicates the first free slot

    public static void addTask(String... params) {
        boolean done = params[0].equals("1");
        Keyword keyword = Keyword.valueOf(params[1]);
        Task task = new Todo(params[2]);
        switch (keyword) {
            case DEADLINE:
                task = new Deadline(params[2], params[3]);
                break;
            case EVENT:
                task = new Event(params[2], params[3], params[4]);
        }
        list.add(task);
        if (done) {
            list.get(total).markAsDone();
        }
        total++;
    }

    public static void addSaveTask(String... params) {
        Keyword keyword = Keyword.valueOf(params[0]);
        Task task = new Todo(params[1]);
        switch (keyword) {
            case DEADLINE:
                task = new Deadline(params[1], params[2]);
                break;
            case EVENT:
                task = new Event(params[1], params[2], params[3]);
        }
        list.add(task);
        total++;
        Storage.getInstance().saveData("0|" + String.join("|", params));

        String resp = "I have added this task for you!\n" + task;
        resp += total > 1
                ? String.format("\nNow you have %d cheems.tasks in your list!", total)
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
                    resp += String.format("%d.%s", i + 1, list.get(i));
                } else {
                    resp += String.format("%d.%s\n", i + 1, list.get(i));
                }
            }
        }
        UI.printWithFormat(resp);
    }


    public static void markAsDone(int index) throws IndexOutOfBoundsException {
        // check for range of index
        if (index >= total) {
            String errMsg = String.format("Sorry you do not have task #%d, " +
                    "try \"list\" to check your current list of cheems.tasks!", index + 1);
            throw new IndexOutOfBoundsException(errMsg);
        }

        list.get(index).markAsDone();
        Storage.getInstance().mark(index, true);

        String resp = "Good job! I've marked this task as done:\n" + list.get(index);
        UI.printWithFormat(resp);
    }

    public static void markAsNotDone(int index) throws IndexOutOfBoundsException {
        // check for range of index
        if (index >= total) {
            String errMsg = String.format("Sorry you do not have task #%d, " +
                    "try \"list\" to check your current list of cheems.tasks!", index + 1);
            throw new IndexOutOfBoundsException(errMsg);
        }

        list.get(index).markAsNotDone();
        Storage.getInstance().mark(index, false);

        String resp = "Okie dokie! I've unmarked it for you:\n" + list.get(index);
        UI.printWithFormat(resp);
    }

    public static void delete(int index) throws IndexOutOfBoundsException {
        // check for range of index
        if (index >= total) {
            String errMsg = String.format("Sorry you do not have task #%d, " +
                    "try \"list\" to check your current list of cheems.tasks!", index + 1);
            throw new IndexOutOfBoundsException(errMsg);
        }

        Task t = list.get(index);
        list.remove(index);
        total--;
        Storage.getInstance().delete(index);

        String resp = "Noted. I've removed this task:\n"
                        + t
                        + String.format("\nNow you have %d cheems.tasks in your list!", total);
        UI.printWithFormat(resp);
    }
}
