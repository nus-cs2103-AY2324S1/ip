package duke;

import duke.Task;

//This class represents the to-do task
public class ToDo extends Task {
    //same as its parent constructor
    public ToDo(String name) {
        super(name);
    }

    //default display for to-do objects
    public String display() {
        if(done) {
            return "[T][X] " + this.name;
        }
        return "[T][] " + this.name;
    }
}
