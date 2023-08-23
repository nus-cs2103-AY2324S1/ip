/**
 * CS2103T Individual project
 * AY2023/24 Semester 1
 *
 * @author Anthony Tamzil
 */
public class ListOfTasks {
    Task[] list;
    int numOfTasks = 0;

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

        System.out.println("added: " + task.getDescription());
    }

    /**
     * Prints out list of tasks to display to the user.
     */
    public void listTasks() {
        for (int i = 0; i < numOfTasks; i++) {
            System.out.println((i + 1) + "." + list[i].toString());
        }
    }
}
