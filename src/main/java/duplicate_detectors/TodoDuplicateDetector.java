package duplicate_detectors;

import tasks.Task;
import tasks.Todo;

import java.util.ArrayList;

public class TodoDuplicateDetector {

    /**
     * This method checks if the given description matches any current Todo tasks.
     * @param description
     * @param taskList
     * @return true if there is a duplicate, else it returns false.
     */
    public boolean checkDuplicates(String description, ArrayList<Task> taskList) {
        for (Task task : taskList) {
            if (task instanceof Todo) {
                Todo todoTask = (Todo) task;
                if (todoTask.getDescription().equals(description)) {
                    return true;
                }
            }
        }
        return false;
    }
}
