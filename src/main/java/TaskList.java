import java.util.ArrayList;

/**
 * A class to hold a list of tasks and perform operations on
 * this list.
 */
public class TaskList {
    /** An ArrayList to hold tasks entered by the User. */
    private ArrayList<Task> tasks;

    public TaskList() {
        this.tasks = new ArrayList<Task>();
    }


    /**
     * Adds the given task to the tasks ArrayList.
     * Prints that the task has been added.
     *
     * @param task The task to add.
     *
     */
    public void addTask(Task task) {
        tasks.add(task);
        System.out.println("\tGot it. I've added this task:");
        System.out.println("\t " + task);
        System.out.println("\tNow you have " + tasks.size() + " tasks in the list.");

    }

    /**
     * Lists the tasks present in tasks ArrayList.
     */
    public void listTasks() {
        System.out.println("\tHere are the tasks in your list:");
        for (int i = 0; i < tasks.size(); i++) {
            System.out.print("\t");
            System.out.print(i + 1);
            System.out.print(". " + tasks.get(i) + "\n");
        }

    }

    /**
     * Marks a given Task.
     *
     * @param index The location of the task in tasks ArrayList.
     */
    public void markTask(int index) {

        tasks.get(index - 1).setAsDone();
        System.out.println("\tNice! I've marked this task as done:");
        System.out.println("\t  " + tasks.get(index - 1));

    }

    /**
     * Marks a given Task as not done.
     *
     * @param index The location of the task in tasks ArrayList.
     */
    public void unmarkTask(int index) {

        tasks.get(index - 1).setAsNotDone();
        System.out.println("\tOk. I have marked this task as not done yet:");
        System.out.println("\t  " + tasks.get(index - 1));
    }

    /**
     * Deletes a given Task from the tasklist.
     *
     * @param index The location of the task in tasks ArrayList.
     */
    public void deleteTask(int index) {
        System.out.println("\tNoted. I have removed this task from the list:");
        System.out.println("\t  " + tasks.get(index - 1));
        tasks.remove(index - 1);
        System.out.println("\tNow you have " + tasks.size() + " tasks in the list.");
    }


    /**
     * Returns the size of the taskList.
     * @return Returns the size of the current taskList.
     */
    public int size() {
        return tasks.size();
    }
}
