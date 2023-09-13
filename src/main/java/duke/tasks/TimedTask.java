package duke.tasks;

import java.time.LocalDateTime;

/**
 * Encapsulates tasks that have a time.
 */
public abstract class TimedTask extends Task implements Comparable<TimedTask> {
    private LocalDateTime start;
    private LocalDateTime end;



    /**
     * Constructor for a TimedTask.
     *
     * @param id The task id
     * @param itemName The item name
     * @param start The start time
     * @param end The end time
     */
    public TimedTask(int id, String itemName, LocalDateTime start, LocalDateTime end) {
        super(id, itemName);
        this.start = start;
        this.end = end;

    }

    /**
     * Constructor for a TimedTask
     * @param itemName The item name
     * @param start The start time
     * @param end The end time
     */
    public TimedTask(String itemName, LocalDateTime start, LocalDateTime end) {
        super(itemName);
        this.start = start;
        this.end = end;

    }

    public LocalDateTime getStart() {
        return start;
    }

    public LocalDateTime getEnd() {
        return end;
    }

    @Override
    public String getTaskType() {
        return "[ ]";
    }




}
