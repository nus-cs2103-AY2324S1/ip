package taskmaster.duplicatecheckers;

import taskmaster.tasks.Task;
import taskmaster.tasks.TaskList;
import taskmaster.tasks.Deadline;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;


public class DuplicateDeadlineChecker {
    public boolean isDuplicateDeadline(String details, String time) {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
        for (Task task : TaskList.list) {
            if (task instanceof Deadline) {
                Deadline deadline = (Deadline) task;
                String byTime = deadline.getStringDate();
                LocalDate byDate = deadline.getLocalDate();
                String description = deadline.getDescription();
                if (byTime != null && byTime.equals(time)) {
                    return description.equalsIgnoreCase(details);
                }

                if (byDate != null && byDate.isEqual(LocalDate.parse(time, formatter))) {
                    return description.equalsIgnoreCase(details);
                }
            }
        }
        return false;
    }
}
