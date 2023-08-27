package duke;

import java.io.Serializable;

/**
 * This class represents a task which is a parent class
 *
 */
public class Task implements Serializable {
    protected String name;
    public Boolean done;

    /** constructor to init this class
     *
     * @param name the name of the task
     */
    public Task (String name) {
        this.name = name;
        this.done = false;
    }

    /** default display for a task
     *
     * @return returns the display
     */
    public String display() {
        if(done) {
            return "[X] " + this.name;
        }
        return "[] " + this.name;
    }
}
