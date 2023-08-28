import java.util.ArrayList;

/**
 * Class to declare a Todo task
 */
public class ToDo extends Task {
    /**
     * Creates a new instance of a ToDo task
     * 
     * @param name Name of task
     */
    public ToDo(String name) {
        super(name, Type.TODO, "");
    }

    /**
     * Prints out the confirmation text output when a todo task is added
     * 
     * @param taskName Name of task
     * @param list List where task is to be added
     */
    public static void printTaskAdded(String taskName, ArrayList<Task> list) {
        System.out.println("You have added a task:");
        System.out.println("\t[T][ ] " + taskName);
        System.out.println("There are now " + list.size() + " tasks in the list");
    }
}