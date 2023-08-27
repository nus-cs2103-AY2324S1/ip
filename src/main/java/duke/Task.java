package duke;

import java.io.Serializable;

//this class represents a task which is a parent class
public class Task implements Serializable {
    public Boolean done;
    protected String name;

    //constructor to init this class
    public Task(String name) {
        this.name = name;
        this.done = false;
    }

    //default display for a task
    public String display() {
        if (done) {
            return "[X] " + this.name;
        }
        return "[] " + this.name;
    }
}
