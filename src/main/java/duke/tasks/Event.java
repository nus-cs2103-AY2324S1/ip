package duke.tasks;

/**
* Event class with start and end time
*/
public class Event extends Task {
    private String from;
    private String to;

    /**
    * constructs the event class
    *
    * @param task the description of the task
    * @param from the begin time of the task
    * @param to the end time of the task
    */
    public Event(String task, String from, String to) {
        super(task);
        this.from = from;
        this.to = to;
    }

    /**
    * returns the status of the event task
    *
    * @return a formatted string of the status of the event
    */
    @Override
    public String getStatus() {
        String time = "(from: " + from + " to: " + to + ")";
        return "[Event]" + super.getStatus() + " " + time;
    }

    /**
    * returns the begin and end time of the event task
    *
    * @return a string containing the begin and end time
    */
    @Override
    public String getTime() {
        return this.from + " " + this.to;
    }

    @Override
    public String toFile() {
        return super.isDone ? ("E | 1 | " + super.task + " | " + this.from + "-" + this.to)
                            : ("E | 0 | " + super.task + " | " + this.from + "-" + this.to);
    }
}
