/**
 * CS2103T Individual project
 * AY2023/24 Semester 1
 *
 * @author Anthony Tamzil
 */
public class ListOfTasks {
    private Task[] list;
    private int numOfTasks = 0;

    /**
     * A constructor to initialize the ListOfTasks class.
     */
    public ListOfTasks() {
        this.list = new Task[100];
    }

    /**
     * Adds a Task object to the list of Tasks.
     *
     * @param task The Task object to be added.
     */
    public void addTask(Task task) {
        list[numOfTasks] = task;
        numOfTasks++;

        System.out.println("Got it. I have added this task to do:");
        System.out.println("  " + task.toString());
        System.out.println("You now have " + numOfTasks + " tasks in the list.");
    }

    /**
     * Marks specific Task in list as completed.
     *
     * @param taskNumber Number of task in list to be mark as completed.
     */
    public void markTaskAsDone(int taskNumber) {
        Task completedTask = list[taskNumber - 1];
        completedTask.markAsDone();

        System.out.println("Good job! I've marked this task as completed:");
        System.out.println("  " + completedTask);
    }

    /**
     * Marks specific Task in list as uncompleted.
     *
     * @param taskNumber Number of task in list to be mark as uncompleted.
     */
    public void markTaskAsNotDone(int taskNumber) {
        Task unmarkedTask = list[taskNumber - 1];
        unmarkedTask.markAsNotDone();

        System.out.println("OK! I've marked this task as not done yet:");
        System.out.println("  " + unmarkedTask);
    }

    /**
     * Prints out list of tasks to display to the user.
     */
    public void listTasks() {
        System.out.println("These are all the tasks in your list:");
        for (int i = 0; i < numOfTasks; i++) {
            System.out.println("  " + (i + 1) + "." + list[i].toString());
        }
    }
}
