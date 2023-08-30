package cheems;

import cheems.tasks.Task;
import cheems.tasks.Event;
import cheems.tasks.Todo;
import cheems.tasks.Deadline;

import java.util.ArrayList;

/**
 * Represents a task list directly interacts with the user.
 * Interacts with Storage class as well to retrieve and update information to the text database.
 */
public class Tasklist {
    private static final ArrayList<Task> list = new ArrayList<>();
    private static int total = 0;  // total also indicates the first free slot

    /**
     * Adds the task to the task list.
     * @param params An array of strings used to create a new task.
     */
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

    /**
     * Adds a new task the user specified to the task list.
     * Updates the database to include this task.
     * @param params An array of strings used to create a new task.
     */
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
                ? String.format("\nNow you have %d tasks in your list!", total)
                : String.format("\nNow you have %d task in your list!", total);
        UI.printWithFormat(resp);
    }


    /**
     * Prints all tasks in the current task list.
     */
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


    /**
     * Marks the task at index in the task list as done.
     * @param index The index of task to be marked done.
     */
    public static void markAsDone(int index) throws IndexOutOfBoundsException {
        // check for range of index
        if (index >= total) {
            String errMsg = String.format("Sorry you do not have task #%d, " +
                    "try \"list\" to check your current list of tasks!", index + 1);
            throw new IndexOutOfBoundsException(errMsg);
        }

        list.get(index).markAsDone();
        Storage.getInstance().mark(index, true);

        String resp = "Good job! I've marked this task as done:\n" + list.get(index);
        UI.printWithFormat(resp);
    }

    /**
     * Marks the task at index in the task list as undone.
     * @param index The index of task to be marked undone.
     */
    public static void markAsNotDone(int index) throws IndexOutOfBoundsException {
        // check for range of index
        if (index >= total) {
            String errMsg = String.format("Sorry you do not have task #%d, " +
                    "try \"list\" to check your current list of tasks!", index + 1);
            throw new IndexOutOfBoundsException(errMsg);
        }

        list.get(index).markAsNotDone();
        Storage.getInstance().mark(index, false);

        String resp = "Okie dokie! I've unmarked it for you:\n" + list.get(index);
        UI.printWithFormat(resp);
    }


    /**
     * Deletes the task at index in the task list and updates the database.
     * @param index The index of task to be deleted.
     */
    public static void delete(int index) throws IndexOutOfBoundsException {
        // check for range of index
        if (index >= total) {
            String errMsg = String.format("Sorry you do not have task #%d, " +
                    "try \"list\" to check your current list of tasks!", index + 1);
            throw new IndexOutOfBoundsException(errMsg);
        }

        Task t = list.get(index);
        list.remove(index);
        total--;
        Storage.getInstance().delete(index);

        String resp = "Noted. I've removed this task:\n" +
                        t +
                        String.format("\nNow you have %d tasks in your list!", total);
        UI.printWithFormat(resp);
    }

    /**
     * Finds and Print the tasks with corresponding keyword in their description.
     * @param args The array of strings that represents the keyword we need to search for.
     */
    public static void find(String[] args) {
        String search = String.join(" ", args);
        ArrayList<Task> tempList = new ArrayList<>();
        int a = 1;
        String resp = "";

        for (int i = 0; i < total; i++) {
            if (list.get(i).getDescription().contains(search)) {
                resp += String.format("%d.%s\n", a, list.get(i));
                a++;
            }
        }

        if (resp == "") {
            resp = "There is no corresponding task in your list!";
        }
        UI.printWithFormat(resp);
    }
}
