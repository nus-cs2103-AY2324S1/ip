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
        if (!input.startsWith("event ")) {
            throw new IllegalArgumentException(
                    String.format("Hey genius, did you mean \"event %s\"...", input.substring(5)));
        } else if (!input.contains("/from") && !input.contains("/to")) {
            throw new IllegalArgumentException("Congratulations you're the only idiot who would leave out both " +
                                                "\"/from\" and \"/to\" flags");
        } else if (!input.contains("/from")) {
            throw new IllegalArgumentException("You need to add a \"/from\" flag, halfwit");
        } else if (!input.contains("/to")) {
            throw new IllegalArgumentException("You need to add a \"/to\" flag, dimwit");
        }
        int fromFlag = input.indexOf("/from");
        int toFlag = input.indexOf("/to");
        return new Event(input.substring(6, fromFlag - 1),
                    input.substring(fromFlag + 6, toFlag - 1),
                    input.substring(toFlag + 4));
    }
}