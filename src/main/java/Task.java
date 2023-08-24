import java.util.*;

class Task {
    boolean done;
    String name;

    Task(boolean done, String name) {
        this.done = done;
        this.name = name;
    }

    /*
     * Create a Task object with default false
     * done status
     */
    Task(String name) {
        this(false, name);
    }


    public void mark() {
        this.done = true;
        }

    public void unmark() {
        this.done = false;

    }
    @Override
    public String toString() {
        if (done) {
            return "[X] " + this.name;
        } else {
            return "[ ] " + this.name;
        }
    }
}

