package duke.task;

import java.time.LocalDateTime;

/**
 * Class to encapsulate the logic of an event in a todo list
 */
public class EventTask extends Task {

    /**
     * The starting time of the event
     */
    private LocalDateTime from;
    /**
     * The ending time of the event
     */
    private LocalDateTime to;

    /**
     * constructor for duke.task.EventTask
     * Stored as EVENT,{marked}{task description},{from},{to}
     *
     * @param from - the starting time of the event
     * @param to   - the ending time of the event
     * @param task - the description of the task created
     */
    public EventTask(LocalDateTime from, LocalDateTime to, String task) {
        super(task);
        this.from = from;
        this.to = to;
    }

    /**
     * constructor for duke.task.EventTask from storage
     * Stored as EVENT,{marked}{task description},{from},{to}
     *
     * @param from       - the starting time of the event
     * @param to         - the ending time of the event
     * @param task       - the description of the task created
     * @param isComplete - if completed
     */
    public EventTask(LocalDateTime from, LocalDateTime to, String task, boolean isComplete) {
        super(task);
        this.from = from;
        this.to = to;
        if (isComplete) {
            this.toggleComplete();
        }
    }

    @Override
    public String toString() {
        return "[E]" + super.toString() + String.format(" (from: %s to: %s)",
                Task.formatDate(this.from), Task.formatDate(this.to));
    }

    /**
     * returns the stored form of this event
     * Stored as EVENT,{task description},{from},{to}
     *
     * @return EVENT,{task description},{from},{to}
     */
    @Override
    public String getStored() {
        return String.join(Task.SEP, new String[] { "EVENT", this.getTask(),
                this.isComplete() ? "1" : "0",
                this.from.toString(), this.to.toString() });
    }
}
