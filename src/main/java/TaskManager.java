// Replace ArrayList to store tasks

import java.lang.reflect.Array;
import java.util.ArrayList;

public class TaskManager {
    private ArrayList<Task> userTasks;

    public TaskManager() {
        this.userTasks = new ArrayList<Task>();
    }

    public void add(Task task) {
        this.userTasks.add(task);
        System.out.println("Got it. I've added this task:");
        System.out.println(task.toString());
        System.out.println("Now you have " + userTasks.size() + " tasks in the list.");

    }

    public void delete(String input) throws DukeException {
        try {
            int taskID = Integer.parseInt(input.substring(7)) - 1;
            this.userTasks.remove(taskID);
            System.out.println("Noted. I've removed this task:");
            System.out.println(userTasks.get(taskID).toString());
            System.out.println("Now you have " + userTasks.size() + " tasks in the list.");
        } catch (NumberFormatException e) {
            throw new DukeException("Please enter a valid task number.");
        } catch (IndexOutOfBoundsException e) {
            throw new DukeException("Please ensure task exists.");
        }
    }

    public void markTaskAsDone(String input) throws DukeException {
        try {
            int taskID = Integer.parseInt(input.substring(5)) - 1;
            this.userTasks.get(taskID).markAsDone();
        } catch (NumberFormatException e) {
            throw new DukeException("Please enter a valid task number.");
        } catch (IndexOutOfBoundsException e) {
            throw new DukeException("Please ensure task exists.");
        }
    }

    public void markTaskAsUndone(String input) throws DukeException {
        try {
            int taskID = Integer.parseInt(input.substring(7)) - 1;
            this.userTasks.get(taskID).markAsUndone();
        } catch (NumberFormatException e) {
            throw new DukeException("Please enter a valid task number.");
        } catch (IndexOutOfBoundsException e) {
            throw new DukeException("Please ensure task exists.");
        }
    }

    public Task get(int taskID) {
        return this.userTasks.get(taskID);
    }

    public int size() {
        return this.userTasks.size();
    }

    @Override
    public String toString() {
        String output = "";
        for (int i = 0; i < userTasks.size(); i++) {
            output += (i + 1) + ". " + userTasks.get(i).toString() + "\n";
        }
        return output;
    }
}
