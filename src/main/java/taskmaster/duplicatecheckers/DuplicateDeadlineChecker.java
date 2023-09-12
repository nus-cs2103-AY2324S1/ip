package taskmaster.duplicatecheckers;

import taskmaster.tasks.Event;
import taskmaster.tasks.Task;
import taskmaster.tasks.TaskList;
import taskmaster.tasks.Deadline;

public class DuplicateDeadlineChecker {
    public boolean isDuplicateDeadline(String description, String time) {
        for (Task task : TaskList.list) {
            if (task instanceof Event) {
                Deadline deadline = (Deadline) task;
                if (deadline.getDescription().equalsIgnoreCase(description) && deadline.getStringDate().equals(time)) {
                    return true;
                }
            }
        }
        return false;
    }
}
