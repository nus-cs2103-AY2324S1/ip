package taskmaster.duplicatecheckers;

import taskmaster.tasks.Task;
import taskmaster.tasks.TaskList;
import taskmaster.tasks.Event;

public class DuplicateEventChecker {
    public boolean isDuplicateEvent(String description, String startingTime) {
        for (Task task : TaskList.list) {
            if (task instanceof Event) {
                Event event = (Event) task;
                if (event.getDescription().equalsIgnoreCase(description) && event.getStartString().equals(startingTime)) {
                    return true;
                }
            }
        }
        return false;
    }
}
