package Workers;

import Tasks.Deadline;
import Tasks.Event;
import Tasks.Task;
import Tasks.Todo;
import duke.IrisException;

import java.util.ArrayList;

/**
 * This class handles any actions/commands that are to be done to the Task objects.
 */
public abstract class TaskWorker {

    public String work(ArrayList<Task> taskList) {
        return "";
    }

    public String work(String[] inputParts, ArrayList<Task> taskList) {
        return "";
    }

    public String work(String[] inputParts, ArrayList<Task> taskList, boolean markAsDone) {
        return "";
    }
}