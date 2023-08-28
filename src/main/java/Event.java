import java.util.ArrayList;
public class Event extends Task{
    protected String from;
    protected String to;

    public Event(String description, String from, String to) {
        super(description);
        this.from = from;
        this.to = to;
    }

    @Override
    public String toString() {
        return "[E]" + super.toString() + "(from: " + from + "to: " + to + ")";
    }
    public String toStringFile() {
        return "E | " + super.toStringFile() + "/from " + from + "/to " + to;
    }


    public static void addEvent(String description, ArrayList<Task> list) throws DukeException {
        String[] event = description.stripTrailing().split("/from |/to ");
        if (event[0].isEmpty()) {
            throw new DukeException("☹ OOPS!!! The description of an Event cannot be empty.");
        }
        if (event.length < 3) {
            throw new DukeException("☹ OOPS!!! Please provide a valid start and end date");
        }

        Event newTask = new Event(event[0], event[1], event[2]);

        list.add(newTask);
        System.out.println(line);
        System.out.println("Got it. I've added the Event:\n\t" + newTask.toString());
        System.out.println("Now you have " + list.size() + " tasks in the list.");
        System.out.println(line);

    }
    public static void addSavedEvent(String description, ArrayList<Task> list, String isMarked) {
        String[] event = description.stripTrailing().split("/from |/to ");
        Event newTask = new Event(event[0], event[1], event[2]);
        list.add(newTask);
        newTask.markFromRead(isMarked);
    }

}
