package peko.tasks;

import peko.exceptions.InvalidTaskException;
import peko.tasks.Task;

public class ToDos extends Task {
    char type = 'T';
    public ToDos(String s) throws InvalidTaskException {
        super(s);
        this.name = s;
    }
    @Override
    public String toString() {
        return "[" + type + "]" + super.toString();
    }

    public String toStore() {
        String state = status ? "0" : "1";
        return "T" + " | " + state + " | " + this.name;
    }
}
