package taskmaster.duplicatecheckers;

import taskmaster.tasks.Deadline;
import taskmaster.tasks.Task;
import taskmaster.tasks.TaskList;
import taskmaster.tasks.Event;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class DuplicateEventChecker {
    public boolean isDuplicateEvent(String details, String startingTime) {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
        for (Task task : TaskList.list) {
            if (task instanceof Event) {
                Event event = (Event) task;
                String startTime = event.getStartString();
                LocalDate startDate = event.getStartDate();
                String description = event.getDescription();
                if (startTime != null && startTime.equals(startingTime)) {
                    return description.equalsIgnoreCase(details);
                }

                if (startDate != null && startDate.isEqual(LocalDate.parse(startingTime, formatter))) {
                    return description.equalsIgnoreCase(details);
                }
            }
        }
        return false;
    }
}
