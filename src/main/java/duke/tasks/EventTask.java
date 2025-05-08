package duke.tasks;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

/**
 * Encapsulates an event with a start and end date and time.
 * Inherits from Task class.
 *
 * @author Wu Jingya
 */
public class EventTask extends Task {
    private LocalDateTime from;
    private LocalDateTime to;

    /**
     * Constructs an EventTask with the specified description and start and end date and time.
     *
     * @param description The task's description.
     * @param from The task's starting date and time.
     * @param to The task's ending date and time.
     */
    public EventTask(String description, LocalDateTime from, LocalDateTime to) {
        super(description);
        this.from = from;
        this.to = to;
    }

    /**
     * Constructs an EventTask with the specified description, start and end date and time and completion status.
     *
     * @param description The task's description.
     * @param from The task's starting date and time.
     * @param to The task's ending date and time.
     * @param isDone Whether the task is completed.
     */
    public EventTask(String description, LocalDateTime from, LocalDateTime to, Boolean isDone) {
        super(description, isDone);
        this.from = from;
        this.to = to;
    }

    @Override
    public String toString() {
        String fromFormatted = from.format(DateTimeFormatter.ofPattern("MMM d yyyy HH:mm"));
        String toFormatted = to.format(DateTimeFormatter.ofPattern("MMM d yyyy HH:mm"));
        return "[E]" + super.toString() + " (from: " + fromFormatted
                + " " + "to: " + toFormatted + ")";
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public String toData() {
        return "E|" + super.toData() + "|" + from + "|" + to;
    }

    @Override
    public boolean equals(Object obj) {
        if (obj == this) {
            return true;
        }
        if (obj instanceof EventTask) {
            boolean hasSameFrom = ((EventTask) obj).from.equals(this.from);
            boolean hasSameTo = ((EventTask) obj).to.equals(this.to);
            return super.equals(obj) && hasSameFrom && hasSameTo;
        }
        return false;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public int compareDate(Task otherTask) {
        if (otherTask instanceof ToDoTask) {
            return -1;
        } else if (otherTask instanceof DeadlineTask) {
            DeadlineTask deadlineTask = (DeadlineTask) otherTask;
            int comparisonResult = deadlineTask.compareByDate(this.from);
            return -comparisonResult;
        } else if (otherTask instanceof EventTask) {
            EventTask otherEvent = (EventTask) otherTask;
            return this.from.compareTo(otherEvent.from);
        }
        return 0;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public int compareType(Task otherTask) {
        if (otherTask instanceof DeadlineTask) {
            return 1;
        } else if (otherTask instanceof EventTask) {
            return 0;
        } else if (otherTask instanceof ToDoTask) {
            return 1;
        }
        return 0;
    }

    /**
     * Compares the Task's starting time with the specified date.
     * Returns an integer that is negative, zero, or positive if the Task's deadline is
     * less than, equal to, or greater than the date respectively.
     *
     * @param date The date to be compared with.
     * @return An integer representing the comparison.
     */
    public int compareFromDate(LocalDateTime date) {
        return this.from.compareTo(date);
    }
}
