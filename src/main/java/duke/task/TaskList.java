package duke.task;

import duke.Ui;
import java.util.ArrayList;

public class TaskList {

    /*
        The task list for the application.
     */
    private ArrayList<Task> tasks;

    /**
     * Constructs a TaskList object with the provided task list.
     *
     * @param tasks The initial list of tasks.
     */
    public TaskList(ArrayList<Task> tasks) {
        this.tasks = tasks;
    }

    /**
     * Constructs a TaskList object without the provided task list.
     */
    public TaskList() {
        this.tasks = new ArrayList<>();
    }

    /**
     * Lists all the tasks in the task list and displays them.
     *
     * @return The tasks in the task list in string format.
     */
    public String listAllTask() {

        String result;
        if (this.tasks.size() == 0) {
            result = "There is not any task yet...";
        } else {
            result = "\n Here " + (tasks.size() > 1 ? "are" : "is") +
                    " the " + (tasks.size() > 1 ? "tasks" : "task") + " in your list: ";
            for (int i = 1; i < tasks.size() + 1; i++) {
                result += "\n" + i + "." + tasks.get(i - 1).toString();
            }
            result += "\n";
        }
        return result;
    }

    /**
     * Copies the contents of the task list into a provided list.
     *
     * @param here The list where the task will be copied.
     */
    public void copyArrInto(ArrayList<String> here) {

        for (Task t : this.tasks) {
            here.add(t.showContentLine());
        }
    }

    /**
     * Returns the number of tasks in the task list.
     *
     * @return The number of tasks in the task list.
     */
    public int size() {
        return this.tasks.size();
    }

    /**
     * Adds a task into the task list`.
     *
     * @param t The task to be added.
     */
    public void add(Task t) {
        this.tasks.add(t);
    }

    /**
     * Retrieves a task of provided index from the task list.
     *
     * @param i The index of the task to retrieve.
     * @return The task at the provided index.
     */
    public Task get(int i) {
        return this.tasks.get(i);
    }

    /**
     * Removes a task of provided index from the task list.
     *
     * @param i The index of the task to remove.
     */
    public void remove(int i) {
        this.tasks.remove(i);
    }

    public ArrayList<Task> getTasks() {
        return this.tasks;
    }
}
