package helpers;

import java.time.LocalDateTime;
import java.util.ArrayList;

import tasks.Task;
import tasks.TaskList;
import tasks.Event;

/**
 * This class is detects when there is a clash between two Events.
 */
public class ClashDetection {

    private ArrayList<Task> tasks;
    private Event newEvent;

    public ClashDetection(ArrayList<Task> tasks, Event newEvent) {
        this.tasks = tasks;
        this.newEvent = newEvent;
    }

    public String constructClashMessage() {
        return "WARNING! Overlap in Event timings detected!\n";
    }

    public boolean detectClash() {
        boolean res = false;

        for (Task t: tasks) {
            if (t instanceof Event) {
                if (isOverlap((Event) t, newEvent)) {
                    res = true;
                    break;
                }
            }
        }
        return res;
    }

    public static boolean isOverlap(Event existingEvent, Event newEvent) {
        System.out.println("1: " + existingEvent.getEnd().isAfter(newEvent.getStart()));
        System.out.println("2: " + newEvent.getEnd().isAfter(existingEvent.getStart()));
        return existingEvent.getEnd().isAfter(newEvent.getStart())
                && newEvent.getEnd().isAfter(existingEvent.getStart());
    }

}
