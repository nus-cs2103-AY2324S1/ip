package duke.freetimefinder;

import duke.TaskList;
import duke.task.Event;

import java.time.LocalDateTime;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

/**
 * FreeTimeFinder allows the user to find the next free timeslot.
 */
public class FreeTimeFinder {
    /**
     * Find the next free timeslot
     *
     * @param tasks The current TaskList to operate on.
     */
    public static void findFreeTime(TaskList tasks) {
        TimeInterval freeTime = null;
        List<TimeInterval> intervals;
        List<Event> events = tasks.getEvents();
        intervals = events.stream().map(event ->
                new TimeInterval(event.getFrom(), event.getTo())).collect(Collectors.toList());
        // Sort the intervals based on their start times
        intervals.sort(Comparator.comparing(TimeInterval::getFrom));

        LocalDateTime currentEnd = LocalDateTime.now();

        // Iterate through the sorted intervals to find free time slots
        for (TimeInterval currentInterval : intervals) {
            if (currentInterval.getFrom().isAfter(currentEnd)) {
                // Found a gap, add it to free time slots
                freeTime = new TimeInterval(currentEnd, currentInterval.getFrom());
                break;
            } else {
                // Update the current end time if the current interval overlaps
                currentEnd = currentEnd.isAfter(currentInterval.getTo()) ? currentEnd : currentInterval.getTo();
            }
        }
        if (freeTime == null) {
            freeTime = new TimeInterval(currentEnd, null);
        }
        System.out.printf("You have free time %s\n", freeTime);
    }

}
