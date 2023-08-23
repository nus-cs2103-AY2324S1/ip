public class Event extends Task {
    String start;
    String end;

    public Event(String msg, String start, String end) {
        super("E", false, msg);
        this.start = start;
        this.end = end;
    }

    @Override
    public String toString() {
        return super.toString() +
                String.format(" (from: %s to: %s)", start, end);
    }

    public static Event newEvent(String input) {
        int fromFlag = input.indexOf("/from");
        int toFlag = input.indexOf("/to");
        return new Event(input.substring(6, fromFlag - 1),
                    input.substring(fromFlag + 6, toFlag - 1),
                    input.substring(toFlag + 4));
    }
}