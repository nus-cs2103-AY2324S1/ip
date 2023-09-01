package duke;

import duke.task.Task;
import java.util.ArrayList;

/**
 * A class to represent a list of tasks.
 */
public class TaskList {
    private ArrayList<Task> taskList;

    /**
     * Constructs a new task list.
     */
    public TaskList() {
        this.taskList = new ArrayList<>();
    }

    /**
     * Constructs a new task list with the given list of tasks.
     *
     * @param tasks The list of tasks to add.
     */
    public TaskList(ArrayList<Task> tasks) {
        this.taskList = tasks;
    }

    /**
     * Adds a task to the list.
     *
     * @param t The task to add.
     */
    public void addTask(Task t) {
        this.taskList.add(t);
        Ui.showLine();
        System.out.println("Got it. I've added this task:");
        System.out.println(t);
        System.out.println("Now you have " + this.taskList.size() + " in the list.");
        Ui.showLine();

    }

    /**
     * Gets the list of tasks.
     *
     * @return The list of tasks.
     */
    public ArrayList<Task> getTasks() {
        return this.taskList;
    }

    /**
     * Deletes a task from the list.
     *
     * @param i The index of the task to delete.
     */
    public void deleteTask(int i) {
        Task t = this.taskList.get(i);
        this.taskList.remove(i);
        int length = this.taskList.size();
        Ui.showLine();
        System.out.println("Noted! I've removed this task:");
        System.out.println(t);
        System.out.println("Now you have " + length + " tasks in the list.");
        Ui.showLine();
    }

    /**
     * Prints the tasks in the list that contain the String s.
     *
     * @param s The string to search among the tasks
     */
    public void findMatching(String s) {
        int index =  1;
        Ui.showLine();
        System.out.println("Here are the matching tasks in your list:");
        for (Task t : this.taskList) {
            if (t.contains(s)) {
                System.out.println(index + "." + t);
                index += 1;
            }
        }
        Ui.showLine();
    }

    /**
     * Prints the tasks in the list.
     */
    public void printTasks() {
        int len =this.taskList.size();
        System.out.println("Here are the tasks in your list:");
        for (int i = 0; i < len; i++) {
            int task_number = i + 1;
            Task t = this.taskList.get(i);
            System.out.println(task_number
                    + "."
                    + t);
        }
    }
}
