package duke.task;

import java.util.ArrayList;

/**
 * The Event class, which is a type of Task class.
 */
public class Event extends Task {

    protected String from;
    protected String to;

    /**
     * A constructor for the Event class.
     * @param description Description of the event.
     * @param from When the event starts.
     * @param to When the event ends.
     */
    public Event(String description, String from, String to) {
        super(description);
        this.from = from;
        this.to = to;
    }


    /**
     * Read the Task data and add it into ArrayList.
     * @param list An ArrayList of Tasks.
     * @param data Data to be read and stored in list.
     */
    public static void readData(ArrayList<Task> list, String data) {
        String splitEvent[] = data.split(" \\| ");


        Task eventTask = new Event(splitEvent[2], splitEvent[3] + " ", splitEvent[4]);
        list.add(eventTask);
        if (splitEvent[1].equals("1")) {
            list.get(list.size()-1).markDoneNoPrint();
        }
    }

    /**
     * Default System.out when this function is called.
     * @return string information in the Event instance.
     */
    @Override
    public String toString() {
        return "[E]" + super.toString() + " (from: " + this.from + "to: " + this.to + ")";
    }
}