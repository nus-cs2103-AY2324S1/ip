import java.util.Scanner;

public class Event extends Task {
    private String from;
    private String to;

    public Event(String description, String from, String to) {
        super(description);
        this.from = from;
        this.to = to;
    }
    public static Event eventDescriptor() {
        System.out.print(Duke.indent + "What is the event? Give in format Event/From/To: ");
        String desc = new Scanner(System.in).nextLine();
        String[] arr = desc.split("/");
        Event ev = new Event(arr[0], arr[1], arr[2]);
        System.out.println(Duke.indent + "Cool! I have added this new task");
        System.out.println(Duke.indent + "  " + ev.toString());
        return ev;
    }

    @Override
    public String toString() {
        return "[E]" + super.toString() + " (from: " + this.from + " to: " + this.to + ")";
    }

}
