import java.util.ArrayList;

/**
 * Represents a list of tasks.
 *
 * @author Anthony Tamzil
 * @version CS2103T Individual Project AY2023/24 Semester 1
 */
public class ListOfTasks {
    private ArrayList<Task> list;
    private int numOfTasks = 0;

    /**
     * A constructor to initialize the ListOfTasks class.
     */
    public ListOfTasks() {
        this.list = new ArrayList<>();
    }

    /**
     * Adds a Task object to the list of Tasks.
     *
     * @param task The Task object to be added.
     */
    public void addTask(Task task, boolean isReadFromFile) {
        list.add(task);
        numOfTasks++;

        if (!isReadFromFile) {
            System.out.println("Got it. I have added this task to do:");
            System.out.println("  " + task.toString());
            System.out.println("You now have " + numOfTasks + " task(s) in the list.");
        }
    }

    /**
     * Marks specific Task in list as completed.
     *
     * @param taskNumber Number of task in list to be mark as completed.
     */
    public void markTaskAsDone(int taskNumber, boolean isReadFromFile) {
        Task completedTask = list.get(taskNumber - 1);
        completedTask.markAsDone();

        if (!isReadFromFile) {
            System.out.println("Good job! I've marked this task as completed:");
            System.out.println("  " + completedTask);
        }
    }

    /**
     * Deletes specific Task in list.
     *
     * @param taskNumber Number of task in list to be deleted.
     */
    public void delete(int taskNumber) {
        numOfTasks--;

        System.out.println("Noted! I have removed this task:");
        System.out.println("  " + list.get(taskNumber - 1).toString());
        System.out.println("You now have " + numOfTasks + " task(s) in the list.");

        list.remove(taskNumber - 1);
    }

    /**
     * Marks specific Task in list as uncompleted.
     *
     * @param taskNumber Number of task in list to be mark as uncompleted.
     */
    public void markTaskAsNotDone(int taskNumber) {
        Task unmarkedTask = list.get(taskNumber - 1);
        unmarkedTask.markAsNotDone();

        System.out.println("OK! I've marked this task as not done yet:");
        System.out.println("  " + unmarkedTask);
    }

    /**
     * Prints out list of tasks to display to the user.
     */
    public void listTasks() {
        System.out.println("These are all the task(s) in your list:");
        for (int i = 0; i < numOfTasks; i++) {
            System.out.println("  " + (i + 1) + "." + list.get(i).toString());
        }
    }

    /**
     * Returns number of tasks in the list
     */
    public int getNumOfTasks() {
        return this.numOfTasks;
    }
}
