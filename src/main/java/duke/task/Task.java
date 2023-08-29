package duke.task;
import java.util.HashMap;
import duke.task.DukeException;

public class Task {
    /**
     * field completed indicates if the task should be marked
     * field name is the name of the task name
     * field ogname is the original name of the task parsed in
     * field type is the tyoe of Task parsed in
     */
    boolean completed;
    String name, ogname, type;

    /**
     *
     * @param name name of the Task object
     */

    public Task(String name) {
        this.completed = false;
        this.name = name;
    }

//
//    public boolean getComplete() {
//        return this.completed;
//    }

    /**
     * method to mark the task as completed
     */
    public void markCompleted() {
        this.completed = true;
    }

    /**
     * method to unmark the task as not completed
     */

    public void markUncompleted() {
        this.completed = false;
    }

    /**
     *
     * @return returns the String format of the Task class
     */
    @Override
    public String toString() {
        if (this.completed) {
            return "[X]" + this.name;
        } else {
            return "[ ]" + this.name;
        }

    }



}
