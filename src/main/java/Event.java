import java.util.ArrayList;

/**
 * Class to declare a Event task
 */
public class Event extends Task {
    /** Start time of the task */
    private String start;

    /** End time of the task */
    private String end;

    /**
     * Creates a new instance of an event task
     * 
     * @param name Name of task
     * @param start Start time of task
     * @param end End time of task
     */
    public Event(String name, String start, String end) {
        super(name, Type.EVENT, " (from: "+ start + " to: " + end + ")");
        this.start = start;
        this.end = end;
    }
    
    /**
     * Prints the confirmation text output when a deadline task is added
     * Also adds the task to the list
     * 
     * @param taskName Name of task
     * @param start Start time of task
     * @param end End time of task
     * @param list List where task is to be added
     */
    public static void printTaskAdded(String taskName, String start, String end, ArrayList<Task> list) {
        System.out.println("You have added a task:");
        System.out.println("\t[E][ ] " + taskName + " (from: "+ start + " to: " + end + ")");
        System.out.println("There are now " + list.size() + " tasks in the list");
    }

    public String getStart() {
        return this.start;
    }

    public String getEnd() {
        return this.end;
    }

}