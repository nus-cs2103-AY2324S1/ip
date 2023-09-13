package Eddie.Tasks;

import Eddie.Tasks.Task;

/**
 * Represents a task to be done no date specified.
 */
public class Todo extends Task {

    public Todo(String name) {
        super(name);
    }

    public String getType() {
        return "T";
    }

    public void tag(String s) {
        super.tag(s);
    }
}
