package duke;

import java.util.ArrayList;

public class Event extends Task {
    protected String from;
    protected String to;

    public Event(String description, String from, String to) {
        super(description);
        this.from = from;
        this.to = to;
    }

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

    public static void newEvent(String text, ArrayList<Task> tasks) {
        String desc = text.split("\\(")[0].trim();
        String from = text.split("from:")[1].split("to:")[0].strip();
        String to = text.split("to:")[1].split("\\)")[0].strip();
        Event updatedEvent = new Event(desc, from, to);
        tasks.add(updatedEvent);
    }

    @Override
    public String toString() {
        return "[E]" + super.toString() + " (from: " + from + " to: " + to + ")";
    }
}
