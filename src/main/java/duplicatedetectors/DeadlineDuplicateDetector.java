package duplicatedetectors;

import java.util.ArrayList;

import tasks.Deadline;
import tasks.Task;

/**
 * This class checks for duplicates.
 */
public class DeadlineDuplicateDetector {

    /**
     * This method checks if the given description and date march any current Deadline tasks.
     * @param description The content of the command
     * @param date The string representing the date of the deadline
     * @param taskList The list of tasks
     * @return true if there is a duplicate, else it returns false.
     */
    public boolean checkDuplicates(String description, String date, ArrayList<Task> taskList) {
        for (Task task : taskList) {
            if (task instanceof Deadline) {
                Deadline deadlineTask = (Deadline) task;
                if (deadlineTask.getDescription().equals(description)
                        && date.equals(deadlineTask.getBy())) {
                    return true;
                }
            }
        }
        return false;
    }
}
