package duke.task.event;

import java.time.LocalDateTime;

import duke.task.Task;



/**
 * Event class is a task that contains a name and an end date and a start date
 */
public class Event extends Task {
    private String from;
    private String to;

    public Event(String name, String from, String to) {
        super(name);
        this.from = from;
        this.to = to;
    }

    public Event(String name, LocalDateTime fromDateTime, LocalDateTime toDateTime) {
        super(name);
        this.from = Task.printDate(fromDateTime);
        this.to = Task.printDate(toDateTime);
    }

    /**
     * This method gives the string representation of a event task when it is in a list
     *
     * @return The String representation of an event Task
     */
    @Override
    public String showTaskinList() {
        return "[E]" + super.showTaskinList() + "(" + "from: " + this.from + " to: " + this.to + ")";
    }
    @Override
    public String printList() {
        return "E | " + super.printList() + " | " + this.from + " to " + this.to;
    }

    public Event reschedule(LocalDateTime fromDateTime, LocalDateTime toDateTime) {
        return new Event(this.showTask(), fromDateTime, toDateTime);
    }

    public Event reschedule(String from, String to) {
        return new Event(this.showTask(), from, to);
    }
}
