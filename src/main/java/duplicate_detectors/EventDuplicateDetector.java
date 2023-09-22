package duplicate_detectors;

import tasks.Task;
import tasks.Event;

import java.util.ArrayList;

public class EventDuplicateDetector {

    /**
     * This method checks if the given description, to, and from String values match any current Event tasks.
     * @param description
     * @param from
     * @param to
     * @param taskList
     * @return true if there is a duplicate, else it returns false.
     */
    public boolean checkDuplicates(String description, String from, String to, ArrayList<Task> taskList) {
        for (Task task : taskList) {
            if (task instanceof Event) {
                Event eventTask = (Event) task;
                if (eventTask.getDescription().equals(description) &&
                from.equals(eventTask.getFrom()) &&
                to.equals(eventTask.getTo())) {
                    return true;
                }
            }
        }
        return false;
    }
}
