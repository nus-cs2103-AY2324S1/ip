package duke.freetimefinder;

import duke.TaskList;
import duke.task.Event;

import java.time.LocalDateTime;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

public class FreeTimeFinder {
    public static void findFreeTime(TaskList tasks) {
        TimeInterval freeTime = null;
        List<TimeInterval> intervals;
        List<Event> events = tasks.getEvents();
        intervals = events.stream().map(event -> new TimeInterval(event.getFrom(), event.getTo())).collect(Collectors.toList());
        // Sort the intervals based on their start times
        intervals.sort(Comparator.comparing(a -> a.from));

        LocalDateTime currentEnd = LocalDateTime.now();

        // Iterate through the sorted intervals to find free time slots
        for (TimeInterval currentInterval : intervals) {
            if (currentInterval.from.isAfter(currentEnd)) {
                // Found a gap, add it to free time slots
                freeTime = new TimeInterval(currentEnd, currentInterval.from);
                break;
            } else {
                // Update the current end time if the current interval overlaps
                currentEnd = currentEnd.isAfter(currentInterval.to) ? currentEnd : currentInterval.to;
            }
        }
        if (freeTime == null) {
            freeTime = new TimeInterval(currentEnd, null);
        }
        System.out.printf("You have free time %s\n", freeTime);
    }

}