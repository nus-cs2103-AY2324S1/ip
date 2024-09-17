package duplicatedetectors;

import java.util.ArrayList;

import tasks.Event;
import tasks.Task;

/**
 * This class checks for duplicates of Event tasks
 */
public class EventDuplicateDetector {

    /**
     * This method checks if the given description, to, and from String values match any current Event tasks.
     * @param description The content of the command
     * @param from The date of the start of the event
     * @param to The date of the end of the event
     * @param taskList The list of tasks
     * @return true if there is a duplicate, else it returns false.
     */
    public boolean checkDuplicates(String description, String from, String to, ArrayList<Task> taskList) {
        for (Task task : taskList) {
            if (task instanceof Event) {
                Event eventTask = (Event) task;
                if (eventTask.getDescription().equals(description)
                        && from.equals(eventTask.getFrom())
                        && to.equals(eventTask.getTo())) {
                    return true;
                }
            }
        }
        return false;
    }
}
