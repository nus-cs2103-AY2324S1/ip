import java.util.ArrayList;

/**
 * Class for tasks
 */
public class Task {
    /** Name of task */
    private String name;
    
    /** Task description, including time */
    private String desc;

    /** Variable to reflect whether a task is finished */
    private Boolean done;

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
        this.done = false;
        this.type = type;
        this.desc = desc;
    }

    /**
     * Marks a task as done
     */
    public void markAsDone() {
        this.done = true;
        System.out.println("You have marked this task as done");
        System.out.println("\t" + this.toString());
    }

    /**
     * Marks a task as not done
     */
    public void markAsNotDone() {
        this.done = false;
        System.out.println("You have marked this task as not done");
        System.out.println("\t" + this.toString());
    }

    /**
     * Deletes a task from the specified list
     * 
     * @param task Task object to be deleted
     * @param list List of tasks
     */
    public static void deleteTask(Task task, ArrayList<Task> list) {
        System.out.println("You have deleted a task:");
        System.out.println("\t" + task.toString());
        list.remove(task);
        System.out.println("There are now " + list.size() + " tasks in the list");
    }

    /**
     * Converts the task object to a String text for display
     * 
     * @return the String representation of the task
     */
    public String convertToString() {
        return "[" + this.getType().substring(0, 1) + "][" + (this.getDone() ? "X" : " ") + "] " + this.name + this.desc;
    }

    public String getName() {
        return this.name;
    }
    
    public Boolean getDone() {
        return this.done;
    }

    public String getType() {
        return this.type.name();
    }
}