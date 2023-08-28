import java.util.ArrayList;

/**
 * Class to declare a Deadline task
 */
public class Deadline extends Task {
    /** Deadline of a deadline task */
    private String deadline;

    /**
     * Creates new instance of a deadline task
     * 
     * @param name Name of task
     * @param deadline Deadline of the task
     */
    public Deadline(String name, String deadline) {
        super(name, Type.DEADLINE, " (by: "+ deadline + ")");
        this.deadline = deadline;
    }
    
    /**
     * Prints the confirmation text output when a deadline task is added
     * Also adds the task to the list
     * 
     * @param taskName Name of task
     * @param deadline Deadline of task
     * @param list List where task is to be added
     */
    public static void printTaskAdded(String taskName, String deadline, ArrayList<Task> list) {
        System.out.println("You have added a task:");
        System.out.println("\t[D][ ] " + taskName + " (by: "+ deadline + ")");
        list.add(new Deadline(taskName, deadline));
        System.out.println("There are now " + list.size() + " tasks in the list");
    }

    public String getDeadline() {
        return this.deadline;
    }

}