package duke;

import java.time.LocalDateTime;
import java.util.ArrayList;

/**
 * The {@code TaskList} class. Deals with storing tasks and performing
 * operations on tasks.
 */
public class TaskList {

    private final ArrayList<Task> tasks;
    private int numOfTasks = 0;
    private int numOfCompletedTasks = 0;

    /**
     * Enumeration of all task types, for type checking purposes.
     */
    public enum TaskType {
        TASK, TODO, DEADLINE, EVENT
    }

    /**
     * Creates a new {@code ArrayList} to store the tasks.
     */
    public TaskList() {
        this.tasks = new ArrayList<>();
    }

    /**
     * Adds a {@code Task} to the {@code ArrayList}.
     *
     * @param t {@code Task} to be added.
     */
    public void add(Task t) {
        tasks.add(t);
        this.numOfTasks++;
        if (t.isCompleted) {
            this.numOfCompletedTasks++;
        }
    }

    /**
     * Gets the {@code Task} object at the specified index.
     *
     * @param i Index of the {@code Task}.
     * @return {@code Task} object at index i.
     */
    public Task get(int i) {
        if (i > -1 && i < numOfTasks) {
            return tasks.get(i);
        } else {
            return null;
        }
    }

    /**
     * Gets the total number of {@code Task}s in the {@code ArrayList}.
     *
     * @return Total number of {@code Task}s.
     */
    public int getNumOfTasks() {
        return this.numOfTasks;
    }

    /**
     * Gets the total number of completed {@code Task}s in the {@code ArrayList}.
     *
     * @return Total number of completed {@code Task}s.
     */
    public int getNumOfCompletedTasks() {
        return this.numOfCompletedTasks;
    }

    /**
     * Checks if the {@code ArrayList} is empty.
     *
     * @return {@code true} iff the {@code ArrayList} is empty; {@code false} otherwise.
     */
    public boolean isEmpty() {
        return this.numOfTasks == 0;
    }

    /**
     * Increments the number of completed tasks when a {@code Task} is marked as complete.
     */
    public void incrementCompletedTasks() {
        this.numOfCompletedTasks++;
    }

    /**
     * Decrements the number of completed tasks when a {@code Task} is marked as incomplete.
     */
    public void decrementCompletedTasks() {
        this.numOfCompletedTasks--;
    }

    /**
     * Checks if there already exists a {@code Task} in the {@code ArrayList} with the
     * given details.
     *
     * @param details Details to be checked.
     * @return {@code true} iff there exists a {@code Task} with the given details; false otherwise.
     */
    public boolean checkDuplicates(String details) {
        for (Task t : tasks) {
            if (details.equals(t.getDetails())) {
                return true;
            }
        }
        return false;
    }

    public boolean hasScheduleClash(LocalDateTime startDateTime, LocalDateTime endDateTime) {
        return getScheduleClash(startDateTime, endDateTime) != null;
    }

    public String getScheduleClash(LocalDateTime startDateTime, LocalDateTime endDateTime) {
        ArrayList<Event> events = new ArrayList<>();
        for (int i = 0; i < this.numOfTasks; i++) {
            Task task = tasks.get(i);
            if (getTaskType(i) == TaskType.EVENT && !task.getCompleted()) {
                events.add((Event) task);
            }
        }
        for (Event e : events) {
            if (checkScheduleClash(startDateTime, endDateTime,
                e.getStartDateTime(), e.getEndDateTime())) {
                return e.getDetails();
            }
        }
        return null;
    }

    /**
     * Checks if two events have overlapping times.
     *
     * @param start1 Start date and time of first event.
     * @param end1   End date and time of first event.
     * @param start2 Start date and time of second event.
     * @param end2   End date and time of second event.
     * @return true if the two events have overlapping times; false otherwise.
     */
    public boolean checkScheduleClash(LocalDateTime start1, LocalDateTime end1,
                                      LocalDateTime start2, LocalDateTime end2) {
        return start1.isBefore(start2) && end1.isAfter(start2)
            || start1.isAfter(start2) && start1.isBefore(end2);
    }

    /**
     * Gets the {@code TaskType} of the {@code Task} at the specified index.
     *
     * @param i Index of the {@code Task}.
     * @return {@code TaskType} of {@code Task} at index i.
     */
    public TaskType getTaskType(int i) {
        Task t = this.get(i);
        if (t instanceof ToDo) {
            return TaskType.TODO;
        } else if (t instanceof Deadline) {
            return TaskType.DEADLINE;
        } else if (t instanceof Event) {
            return TaskType.EVENT;
        } else {
            return TaskType.TASK;
        }
    }

    /**
     * Removes a {@code Task} from the {@code ArrayList}.
     *
     * @param t {@code Task} to be removed.
     */
    public void remove(Task t) {
        tasks.remove(t);
        this.numOfTasks--;
        if (t.isCompleted) {
            this.numOfCompletedTasks--;
        }
    }
}
