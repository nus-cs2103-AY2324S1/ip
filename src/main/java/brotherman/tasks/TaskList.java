package brotherman.tasks;

import java.util.ArrayList;
/**
 * Represents a list of tasks
 */
public class TaskList {

    /**
     * List of tasks
     */
    private ArrayList<Task> taskList;

    /**
     * Constructor for TaskList
     */
    public TaskList() {
        this.taskList = new ArrayList<>();
    }

    /**
     * Constructor for TaskList
     * @param taskList List of tasks
     */
    public TaskList(ArrayList taskList) {this.taskList = taskList;}

    /**
     * Adds a task to the task list
     * @param task Task to be added
     */
    public void add(Task task) {
        this.taskList.add(task);
    }

    /**
     * Deletes a task from the task list
     * @param num Task number to be deleted
     */
    public void delete(int num) {
        if (num < 0 || num >= taskList.size()) {
            System.out.println("Brotherman the value you put in wrong.  Try again.");
        } else {
            System.out.println(
                            "The task is now deleted Brotherman \n"
                            + taskList.get(num).toString()
            );
            taskList.remove(num);
        }
    }


    /**
     * Returns the task list
     * @return Task list
     */
    public ArrayList list() {

        return this.taskList;
    }

    /**
     * Returns the task at the specified index
     * @param index Index of the task
     * @return Task at the specified index
     */
    public int size() {
        return this.taskList.size();
    }

    /**
     * Returns the task at the specified index
     * @param index Index of the task
     * @return Task at the specified index
     */
    public void markDone(int num) {
        if (num < 0 || num >= taskList.size()) {
            System.out.println("Brotherman the value you put in wrong.  Try again.");
        } else {
            taskList.get(num).markAsDone();
            System.out.println(
                    "The task is done Brotherman \n"
                    + taskList.get(num).toString()
            );
        }
    }

    /**
     * Returns the task at the specified index
     * @param index Index of the task
     * @return Task at the specified index
     */
    public void markUndone(int num) {
        if (num < 0 || num >= taskList.size()) {
            System.out.println("Brotherman the value you put in wrong.  Try again.");
        } else {
            taskList.get(num).unmarkAsDone();
            System.out.println(
                    "The task is now undone Brotherman \n"
                    + taskList.get(num).toString()
            );
        }
    }
}
