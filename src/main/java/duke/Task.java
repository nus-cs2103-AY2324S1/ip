package duke;

import java.io.Serializable;

/**
 * This class represents a task which is a parent class
 *
 */
public class Task implements Serializable {
    public Boolean isDone;
    protected String name;


    /**
     * Constructor to init this class
     *
     * @param name the name of the task
     */
    public Task(String name) {
        this.name = name;
        this.isDone = false;
    }


    /**
     * Default display for a task
     *
     * @return returns the display
     */
    public String display() {
        assert isDone != null : "done is not initialised";
        if (isDone) {
            return "[X] " + this.name;
        }
        return "[] " + this.name;
    }
}
