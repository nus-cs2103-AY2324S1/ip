package duke.task;
import java.util.HashMap;
import duke.task.DukeException;

public class Task {
    boolean completed;
    String name, ogname, type;

    public Task(String name) {
        this.completed = false;
        this.name = name;
    }

    public boolean getComplete() {
        return this.completed;
    }

    public void markCompleted() {
        this.completed = true;
    }

    public void markUncompleted() {
        this.completed = false;
    }


    @Override
    public String toString() {
        if (this.completed) {
            return "[X]" + this.name;
        } else {
            return "[ ]" + this.name;
        }

    }



}
