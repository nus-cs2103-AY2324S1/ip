package ballsorting;
import java.util.ArrayList;

public class TaskList {
    ArrayList<Task> tasks;

    /**
     * Creates a new instance of TaskList.
     */
    public TaskList() {
        this.tasks = new ArrayList<>();
    }

    /**
     * Creates a new instance of TaskList.
     * @param tasks Tasks stored in storage.
     */
    public TaskList(ArrayList<Task> tasks) {
        this.tasks = tasks;
    }

    /**
     * Adds a new Task to the TaskList.
     * @param t New Task.
     */
    public void addTask(Task t) {
        tasks.add(t);
        System.out.println("Got it. I've added this task:");
        System.out.println(t.toString());
        System.out.println("Now you have " + tasks.size() + " tasks in the list.");
        System.out.println(Ballsorter.line);
    }

    /**
     * Adds a new Task to the TaskList without printing.
     * @param t New Task.
     */
    public void addTaskSilent(Task t) {
        tasks.add(t);
    }

    /**
     * Deletes a task.
     * @param target Index of the task in the list.
     */
    public void deleteTask(int target) {
        if (target >= tasks.size()) {
            System.out.println("☹ OOPS!!! This task does not exist");
            System.out.println(Ballsorter.line);
        } else {
            System.out.println("Noted. I've removed this task:");
            System.out.println(tasks.get(target).toString());
            tasks.remove(target);
            System.out.println("Now you have " + tasks.size() + " tasks in the list.");
            System.out.println(Ballsorter.line);
        }
    }

    /**
     * Marks a task as done.
     * @param target Index of task in list.
     */
    public void markTask(int target) {
        if (target >= tasks.size()) {
            System.out.println("☹ OOPS!!! This task does not exist");
            System.out.println(Ballsorter.line);
        } else {
            System.out.println("Nice! I've marked this task as done:");
            System.out.println(tasks.get(target).markDone());
            System.out.println(Ballsorter.line);
        }
    }

    /**
     * Unmarks a task as not done.
     * @param target Index of task in list.
     */
    public void unmarkTask(int target) {
        if (target >= tasks.size()) {
            System.out.println("☹ OOPS!!! This task does not exist");
            System.out.println(Ballsorter.line);
        } else {
            System.out.println("OK, I've marked this task as not done yet:");
            System.out.println(tasks.get(target).markNotDone());
            System.out.println(Ballsorter.line);
        }
    }

    /**
     * Prints the list of tasks.
     */
    public void printList() {
        if (tasks.size() == 0) {
            System.out.println("You do not have any tasks yet ☹");
        }
        for (int i = 0; i < tasks.size(); i++) {
            int temp = i + 1;
            System.out.println(temp + ". " + tasks.get(i).toString());
        }
    }

    /**
     * Returns a String that can be stored in storage.
     * @return String representation of all tasks stored in the list, formatted to be read on next startup.
     */
    public String storeList() {
        StringBuilder store = new StringBuilder();
        for (int i = 0; i < tasks.size(); i++) {
            store.append(String.format(tasks.get(i).toStorageString() + "%n"));
        }
        return store.toString();
    }
}
