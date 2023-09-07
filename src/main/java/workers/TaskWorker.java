package workers;

import java.util.ArrayList;

import tasks.Task;

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
