/**
 * Class to encapsulate the logic of an event in a todo list
 */
public class Event extends Task {

    /**
     * The starting time of the event
     */
    private final String from;

    /**
     * The ending time of the event
     */
    private final String to;

    /**
     * constructor for Event
     * Stored as EVENT,{marked}{task description},{from},{to}
     *
     * @param from - the starting time of the event
     * @param to   - the ending time of the event
     * @param task - the description of the task created
     */
    public Event(String from, String to, String task) {
        super(task);
        this.from = from;
        this.to = to;
    }

    /**
     * constructor for Event from storage
     * Stored as EVENT,{marked}{task description},{from},{to}
     *
     * @param from      - the starting time of the event
     * @param to        - the ending time of the event
     * @param task      - the description of the task created
     * @param completed - if completed
     */
    public Event(String from, String to, String task, boolean completed) {
        super(task);
        this.from = from;
        this.to = to;
        if (completed) {
            this.toggleCompleted();
        }
    }

    @Override
    public String toString() {
        return "[E]" + super.toString() + String.format(" (from: %s to: %s)", this.from, this.to);
    }

    /**
     * returns the stored form of this event
     * Stored as EVENT,{task description},{from},{to}
     *
     * @return EVENT,{task description},{from},{to}
     */
    @Override
    public String getStored() {
        return String.join(Task.SEP, new String[]{"EVENT", this.getTask(), this.isCompleted() ? "1" : "0", this.isCompleted() ? "1" : "0", this.from, this.to});
    }
}