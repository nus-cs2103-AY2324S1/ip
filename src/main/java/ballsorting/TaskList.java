package ballsorting;
import java.util.ArrayList;

/**
 * Encapsulates a list of Tasks entered by the user.
 */
public class TaskList {
    private ArrayList<Task> tasks;

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
    public String addTask(Task t) {
        tasks.add(t);
        StringBuilder output = new StringBuilder();
        output.append("Got it. I've added this task:\n");
        output.append(t.toString());
        output.append("\n");
        output.append("Now you have " + tasks.size() + " tasks in the list.");
        return output.toString();
    }

    /**
     * Deletes a task.
     * @param target Index of the task in the list.
     */
    public String deleteTask(int target) {
        if (target >= tasks.size()) {
            return "☹ OOPS!!! This task does not exist";
        } else {
            StringBuilder output = new StringBuilder();
            output.append("Noted. I've removed this task:\n");
            output.append(tasks.get(target).toString());
            output.append("\n");

            tasks.remove(target);

            output.append("Now you have " + tasks.size() + " tasks in the list.");

            return output.toString();
        }
    }

    /**
     * Marks a task as done.
     * @param target Index of task in list.
     */
    public String markTask(int target) {
        if (target >= tasks.size()) {
            return "☹ OOPS!!! This task does not exist";
        } else {
            StringBuilder output = new StringBuilder();
            output.append("Nice! I've marked this task as done:\n");
            output.append(tasks.get(target).markDone());
            return output.toString();
        }
    }

    /**
     * Unmarks a task as not done.
     * @param target Index of task in list.
     */
    public String unmarkTask(int target) {
        if (target >= tasks.size()) {
            return "☹ OOPS!!! This task does not exist";
        } else {
            StringBuilder output = new StringBuilder();
            output.append("OK, I've marked this task as not done yet:");
            output.append(tasks.get(target).markNotDone());
            return output.toString();
        }
    }

    /**
     * Prints the list of tasks.
     */
    public String getStringList() {
        if (tasks.size() == 0) {
            return "You do not have any tasks yet ☹";
        }
        StringBuilder output = new StringBuilder();
        for (int i = 0; i < tasks.size(); i++) {
            int temp = i + 1;
            output.append(temp + ". " + tasks.get(i).toString() + "\n");
        }
        return output.toString();
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

    /**
     * Prints the list of tasks matching the search input.
     * @param searchString search input the user keys in
     */
    public String getSearchList(String searchString) {
        ArrayList<Task> searchList = new ArrayList<>();
        for (int i = 0; i < tasks.size(); i++) {
            if (tasks.get(i).description.contains(searchString)) {
                searchList.add(tasks.get(i));
            }
        }
        if (searchList.isEmpty()) {
            return "☹ OOPS!!! No tasks found";
        } else {
            StringBuilder output = new StringBuilder();
            for (int j = 0; j < searchList.size(); j++) {
                int temp = j + 1;
                output.append(temp + ". " + searchList.get(j).toString() + "\n");
            }
            return output.toString();
        }
    }
    public boolean isDuplicate(String desc) {
        for (int i = 0; i < tasks.size(); i++) {
            if (tasks.get(i).description.equals(desc)) {
                return true;
            }
        }
        return false;
    }
}
