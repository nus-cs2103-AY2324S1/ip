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

    /**
     * This method gives the string representation of an Event task when it is in the saved list
     *
     * @return The String representation of a Event task in the saved list
     */
    @Override
    public String printList() {
        return "E | " + super.printList() + " | " + this.from + " to " + this.to;
    }

    /**
     * Create a new Event object with new from and to time in DateTime
     *
     * @param fromDateTime The new start time of the event in DateTime
     * @param toDateTime The new end time of the event in DateTime
     * @return an Event object with new from and to time in DateTime
     */
    public Event reschedule(LocalDateTime fromDateTime, LocalDateTime toDateTime) {
        return new Event(this.showTask(), fromDateTime, toDateTime);
    }
    /**
     * Create a new Event object with new from and to time
     *
     * @param from The new start time of the event
     * @param to The new end time of the event
     * @return an Event object with new from and to time
     */
    public Event reschedule(String from, String to) {
        return new Event(this.showTask(), from, to);
    }
}
