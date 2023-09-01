package Duke;

/**
 * The Event class represents an event task for the Duke program.
 * It extends the SingleTask class and provides additional methods specific to event tasks.
 */
public class Event extends SingleTask {
    private String from;
    private String to;

    /**
     * Constructs a new Event object with the given description, start time, and end time.
     * @param description the description of the Event task
     * @param from the start time of the Event
     * @param to the end time of the Event
     */
    public Event(String description, String from, String to) {
        super(description);
        this.from = from;
        this.to = to;
    }

    /**
     * Marks the task as done and displays a message to the user.
     */
    public void mark() {
        this.isDone = true;
        System.out.println("Ok boy i mark for you already \n" +
                "[" +this.getStatusIcon() +"] " + this.description);

    }

    /**
     * Returns the status icon of the task.
     * @return a string representing the status icon of the task
     */
    public String getStatusIcon() {
        return (this.isDone ? "X" : " ");
    }

    /**
     * Unmarks the task as done and displays a message to the user.
     */
    public void unmark() {
        this.isDone = false;
        System.out.println("Ok boy I unmark for you already \n" +
                "[" +this.getStatusIcon() +"] " + this.description);
    }

    /**
     * Returns a string representation of the task when it is added to a list.
     * @return a string representing the task when it is added
     */
    @Override
    public String toString() {
        return "OK DONE ALR added your Event ah:\n" +
                "[E][" + getStatusIcon() + "] " + this.description +"(from: "+ this.from + " to: " + this.to + ")";
    }

    /**
     * Returns a string representation of the task for displaying in a list.
     * @return a string representing the task in a list
     */
    @Override
    public String listString() {
        return ". [E][" + getStatusIcon() + "] " + this.description +"(from: "+ this.from + " to: " + this.to + ")";
    }

    /**
     * Returns a string representation of the task when it is removed from the TaskList.
     * @return a string representing the task when it is removed
     */
    @Override
    public String remove() {
        return "OK DONE ALR removed your Event ah:\n" +
                "[E][" + getStatusIcon() + "] " + this.description +"(from: "+ this.from + " to: " + this.to + ")";
    }

    /**
     * Returns a string representation of the task for saving to a file.
     * @return a string representing the task in a save file
     */
    @Override
    public String toSaveString() {
        return "E|" + (this.isDone ? "1" : "0") + "|" + this.description + "|" + this.from + "|" + this.to;
    }
}

