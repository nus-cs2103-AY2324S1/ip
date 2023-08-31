package duke.utilities;
import java.io.Serializable;

/**
 * Class for tasks
 */
public class Task implements Serializable {
    /** Name of task */
    private String name;
    
    /** Task description, including time */
    private String desc;

    /** Variable to reflect whether a task is finished */
    private boolean isDone;

    /** Type of task, can be either a todo, deadline, or event */
    private Type type;

    /**
     * Creates a new instance of a Task object
     * 
     * @param name Name of task
     * @param type Type of task
     * @param desc Task description, which includes by, to, and from
     */
    public Task(String name, Type type, String desc) {
        this.name = name;
        this.isDone = false;
        this.type = type;
        this.desc = desc;
    }

    /**
     * Marks a task as done
     */
    public void markAsDone() {
        this.isDone = true;
        System.out.println("You have marked this task as done");
        System.out.println("\t" + this.convertToString());
    }

    /**
     * Marks a task as not done
     */
    public void markAsNotDone() {
        this.isDone = false;
        System.out.println("You have marked this task as not done");
        System.out.println("\t" + this.convertToString());
    }

    /**
     * Converts the task object to a String text for display
     * 
     * @return the String representation of the task
     */
    public String convertToString() {
        return "[" + this.getType().charAt(0) + "][" + (this.isDone() ? "X" : " ") + "] " + this.getName() + this.desc;
    }

    public String getName() {
        return this.name;
    }
    
    public boolean isDone() {
        return this.isDone;
    }

    public String getType() {
        return this.type.name();
    }
}