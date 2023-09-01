import java.util.ArrayList;
import java.util.Arrays;

public class Event extends Task {
    protected String from;
    protected String to;

    public Event(String description, String from, String to) {
        super(description);
        this.from = from;
        this.to = to;
    }

    public Event(String description, String from, String to, boolean isDone) {
        super(description);
        this.from = from;
        this.to = to;
        this.isDone = isDone;
    }

    /**
     * Generates an Event with description given in the input.
     * @param input input entered by user.
     * @return an Event.
     */
    public static Event generateEventFromInput(String input) throws EmptyEventDescriptionException{
        if (input.split(" ").length == 1) {
            throw new EmptyEventDescriptionException(
                    Action.HORIZONTAL_LINE + "\n" +
                    "You did not provide any description to this Event.\n" +
                    "To add an Event, enter \"event <description> /from <startTime> /to <endTime>\".\n" +
                    Action.HORIZONTAL_LINE);
        }

        String[] wordsSplitBySlash = input.split("/");
        String front = wordsSplitBySlash[0];
        String[] frontWords = front.split(" ");
        ArrayList<String> desWords = new ArrayList<>(Arrays.asList(frontWords).subList(1, frontWords.length));
        String des = String.join(" ", desWords);

        String middle = wordsSplitBySlash[1];
        String[] midWords = middle.split(" ");
        ArrayList<String> startWords = new ArrayList<>(Arrays.asList(midWords).subList(1, midWords.length));
        String start = String.join(" ", startWords);

        String back = wordsSplitBySlash[2];
        String[] backWords = back.split(" ");
        ArrayList<String> endWords = new ArrayList<>(Arrays.asList(backWords).subList(1, backWords.length));
        String end = String.join(" ", endWords);

        return new Event(des, start, end);
    }

    @Override
    public String toString() {
        return "[E]" + super.toString() + " (from: " + this.from + " to: " + this.to + ")";
    }
}
