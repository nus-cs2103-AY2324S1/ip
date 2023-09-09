package duke;

import java.util.ArrayList;

import task.Task;

/**
 * TaskList represent the list of tasks of the user.
 */
public class TaskList {
    private static ArrayList<Task> taskList;

    /**
     * The constructor of TaskList.
     *
     * @param taskList The ArrayList of tasks of the user.
     */
    public TaskList(ArrayList<Task> taskList) {
        this.taskList = taskList;
    }

    /**
     * To print out all the tasks in the user's list of tasks.
     */
    public String listTasks() {
        if (taskList.isEmpty()) {
            return "There are currently no tasks in your list.";
        } else {
            int i = 1;
            String listMessage = "Here are the tasks in your list: \n";
            for (Task t : this.taskList) {
                listMessage += i + ". " + t.toString() + "\n";
                i++;
            }
            return listMessage;
        }
    }

    /**
     * Marks the specific task in the user's list of tasks as completed.
     *
     * @param num The index of the task which is to be mark as completed.
     */
    public String markTask(int num) {
        Task t = taskList.get(num - 1);
        t.markDone();
        return "Nice! I've marked this task as done: \n" + t;
    }

    /**
     * Marks the specific task in the user's list of tasks as incomplete.
     *
     * @param num The index of the task which is to be mark as incomplete.
     */
    public String unmarkTask(int num) {
        Task t = taskList.get(num - 1);
        t.markUndone();
        return "Nice! I've un-marked this task: \n" + t;
    }

    /**
     * Adds the task into the user's list of tasks.
     *
     * @param task The task to be added.
     */
    public String addTask(Task task) {
        this.taskList.add(task);
        return "Got it. Task successfully added: \n"
                + task.toString()
                + "\nNow you have " + taskList.size() + " tasks in the list.";
    }

    /**
     * Deletes the specific task from the user's list of tasks.
     *
     * @param num The index of the task to be deleted.
     */
    public String deleteTask(int num) {
        Task deletedTask = taskList.remove(num - 1);
        return "Noted. I've removed this task: \n"
                + deletedTask.toString()
                + "\nNow you have " + taskList.size() + " tasks in the list.";
    }

    /**
     * Finds the tasks in the list of tasks with the given keyword.
     *
     * @param keyword The keyword the user wants to search for in the tasks.
     */
    public String findTask(String keyword) {
        if (taskList.isEmpty()) {
            return "You have no tasks in your list yet :O";
        } else {
            String findMessage = "Here are the matching tasks in your list: \n";
            int i = 1;
            for (Task t : this.taskList) {
                String taskString = t.toString();
                if (taskString.contains(keyword)) {
                    findMessage += i + ". " + taskString + "\n";
                    i++;
                }
            }
            return findMessage;
        }
    }

    /**
     * A getter function to get the user's current list of tasks.
     *
     * @return The user's current list of tasks.
     */
    public static ArrayList<Task> getTaskList() {
        return taskList;
    }
}
