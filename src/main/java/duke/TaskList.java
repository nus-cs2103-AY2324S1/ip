package duke;

import java.util.ArrayList;

/**
 * Contains the task list with operations such as
 * add or delete to alter the list.
 */
public class TaskList {
    /** List of tasks. */
    ArrayList<Task> list;

    /** Number of tasks in the list. */
    int count;

    public TaskList(ArrayList<Task> list) {
        this.list = list;
        this.count = list.size();
    }

    /**
     * Prints the list.
     */
    public String printList() {
        StringBuilder fullList = new StringBuilder("Here are the tasks in your list:\n");
        for (int i = 0; i < count; i++) {
            fullList.append(i + 1).append(". ").append(list.get(i)).append("\n");
        }
        return fullList.toString();
    }

    /**
     * Add tasks to the list.
     *
     * @param task Task to be added into the list.
     */
    public String addTask(Task task) {
        this.list.add(task);
        count++;
        return "Got it. I've added this task:\n"
                       + list.get(count - 1) + "\n"
                       + "Now you have " + (count) + " tasks in the list.";

    }

    /**
     * Delete tasks from the list.
     *
     * @param index Index of the item to be removed from the list.
     */
    public String deleteTask(int index) {
        Task deletedTask = list.get(index);
        this.list.remove(index);
        count--;
        return "Noted. I've removed this task:\n"
                       + deletedTask + "\n"
                       + "Now you have " + count + " tasks in the list";
    }

    /**
     * Gets the task from the list.
     *
     * @param index Index of item to be accessed.
     * @return Task to be accessed.
     */
    public Task getTask(int index) {
        for (int i = 0; i < count; i++) {
            if (index == i) {
                return list.get(i);
            }
        }
        return null;
    }

    /**
     * Finds tasks.
     *
     * @param input task to be found.
     * @param list List in which the task is to be found.
     * @return Found tasks.
     */
    public String findTask(String input, TaskList list) {
        ArrayList<Task> foundTasks = new ArrayList<>();
        StringBuilder output = new StringBuilder("Here are the matching tasks in your list:\n");
        for (Task task : this.list) {
            if (task.toString().contains(input)) {
                foundTasks.add(task);
            }
        }
        if (foundTasks.size() == 0) {
            output = new StringBuilder("Oops, there are no matching tasks in your list");
        } else {
            for(int j = 0; j < foundTasks.size(); j++) {
                output.append(j + 1).append(".")
                        .append(foundTasks.get(j).toString())
                        .append("\n");;
            }
        }
        return output.toString();
    }
}
