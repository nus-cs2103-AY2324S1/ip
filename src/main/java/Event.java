/**
 * Event class is a task that contains a name and an end date and a start date
 */
public class Event extends Task{
    private String from;
    private String to;
    public Event (String name, String from, String to) {
        super(name);
        this.from = from;
        this.to = to;
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
}
