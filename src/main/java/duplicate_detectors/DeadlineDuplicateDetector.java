package duplicate_detectors;

import tasks.Deadline;
import tasks.Task;

import java.util.ArrayList;

public class DeadlineDuplicateDetector {

    /**
     * This method checks if the given description and date march any current Deadline tasks.
     * @param description
     * @param date
     * @param taskList
     * @return true if there is a duplicate, else it returns false.
     */
    public boolean checkDuplicates(String description, String date, ArrayList<Task> taskList) {
        for (Task task : taskList) {
            if (task instanceof Deadline) {
                Deadline deadlineTask = (Deadline) task;
                if (deadlineTask.getDescription().equals(description) &&
                        date.equals(deadlineTask.getBy())) {
                    return true;
                }
            }
        }
        return false;
    }
}
