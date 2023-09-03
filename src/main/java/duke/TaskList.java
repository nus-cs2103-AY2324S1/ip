package duke;

import task.Task;

import java.util.ArrayList;

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
    public void listTasks() {
        if (taskList.isEmpty()) {
            System.out.println("There are currently no tasks in your list"
                    + Ui.SEPARATOR);
        } else {
            int i = 1;
            System.out.println("Here are the tasks in your list: ");
            for (Task t : this.taskList) {
                System.out.println(i + ". " + t.toString());
                i++;
            }
            System.out.println(Ui.SEPARATOR);
        }
    }

    /**
     * Marks the specific task in the user's list of tasks as completed.
     *
     * @param num The index of the task which is to be mark as completed.
     */
    public void markTask(int num) {
        Task t = taskList.get(num - 1);
        t.markDone();
        System.out.println("Nice! I've marked this task as done: \n"
                + t
                + Ui.SEPARATOR);
    }

    /**
     * Marks the specific task in the user's list of tasks as incomplete.
     *
     * @param num The index of the task which is to be mark as incomplete.
     */
    public void unmarkTask(int num) {
        Task t = taskList.get(num - 1);
        t.markUndone();
        System.out.println("Nice! I've un-marked this task: \n"
                + t
                + Ui.SEPARATOR);
    }

    /**
     * Adds the task into the user's list of tasks.
     *
     * @param task The task to be added.
     */
    public void addTask(Task task) {
        this.taskList.add(task);
        System.out.println("Got it. Task successfully added: \n"
                + task.toString()
                + "\nNow you have " + taskList.size() + " tasks in the list."
                + Ui.SEPARATOR);
    }

    /**
     * Deletes the specific task from the user's list of tasks.
     *
     * @param num The index of the task to be deleted.
     */
    public void deleteTask(int num) {
        Task deletedTask = taskList.remove(num - 1);
        System.out.println("Noted. I've removed this task: \n"
                + deletedTask.toString()
                + "\nNow you have " + taskList.size() + " tasks in the list."
                + Ui.SEPARATOR);
    }

    public void findTask(String keyword) {
        if (taskList.isEmpty()) {
            System.out.println("You have no tasks in your list yet :O");
        } else {
            System.out.println("Here are the matching tasks in your list:");
            int i = 1;
            for (Task t : this.taskList) {
                String taskString = t.toString();
                if (taskString.contains(keyword)) {
                    System.out.println(i + ". " + taskString);
                    i++;
                }
            }
            System.out.println(Ui.SEPARATOR);
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
