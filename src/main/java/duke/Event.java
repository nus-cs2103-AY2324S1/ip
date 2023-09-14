package duke;

import java.time.LocalDate;
import java.util.ArrayList;

/**
 * Event in the task list.
 */
public class Event extends Task {
    protected String from;
    protected String to;

    public Event(String description, String from, String to) {
        super(description);
        this.from = from;
        this.to = to;
    }

    /**
     * Prints out new Event in the task list.
     *
     * @param userOutput User command.
     * @param inputList  List of tasks.
     * @throws EmptyException If command is invalid.
     */
    public static void setEvent(String userOutput, TaskList inputList) throws EmptyException {
        try {
            String newDes = userOutput.split("event")[1].split("/from")[0].strip();
            String newFrom = userOutput.split("/from")[1].split("/to")[0].strip();
            String newTo = userOutput.split("/to")[1].strip();
            Event newEvent = new Event(newDes, newFrom, newTo);
            inputList.add(newEvent);
            System.out.println("Got it. I've added this task:");
            System.out.println(newEvent);
            System.out.println("Now you have " + inputList.size() + " tasks in the list.");
        } catch (Exception e) {
            throw new EmptyException("event");
        }
    }

    /**
     * Updates the storage with new Event.
     *
     * @param text  Event from the storage.
     * @param tasks Task list stored in the storage.
     */
    public static void setNewEvent(String text, ArrayList<Task> tasks) {
        String desc = text.split("\\(")[0].trim();
        String from = text.split("from:")[1].split("to:")[0].strip();
        String to = text.split("to:")[1].split("\\)")[0].strip();
        Event updatedEvent = new Event(desc, from, to);
        tasks.add(updatedEvent);
    }

    public void update(String userOutput) {
        String[] splitDes = userOutput.split("/desc");
        if (splitDes.length > 1) {
            this.description = splitDes[1].split("\"")[1];
        }
        String[] splitFrom = userOutput.split("/from");
        if (splitFrom.length > 1) {
            this.from = splitFrom[1].split(" ")[1].strip();
        }
        String[] splitTo = userOutput.split("/to");
        if (splitTo.length > 1) {
            this.to = splitTo[1].split(" ")[1].strip();
        }
        System.out.println("Got it. Task is successfully updated:");
        System.out.println(this);
    }

    @Override
    public String toString() {
        return "[E]" + super.toString() + " (from: " + from + " to: " + to + ")";
    }
}
