import java.util.ArrayList;

public class Event extends Task {
    protected String from;
    protected String to;

    public Event(String description, String from, String to) {
        super(description);
        this.from = from;
        this.to = to;
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

        String front = input.split("/")[0];
        String[] frontWords = front.split(" ");
        ArrayList<String> desWords = new ArrayList<>();
        for (int i = 1; i < frontWords.length; i++) {
            desWords.add(frontWords[i]);
        }
        String des = String.join(" ", desWords);

        String middle = input.split("/")[1];
        String[] midWords = middle.split(" ");
        ArrayList<String> startWords = new ArrayList<>();
        for (int i = 1; i < midWords.length; i++) {
            startWords.add(midWords[i]);
        }
        String start = String.join(" ", startWords);

        String back = input.split("/")[2];
        String[] backWords = back.split(" ");
        ArrayList<String> endWords = new ArrayList<>();
        for (int i = 1; i < backWords.length; i++) {
            endWords.add(backWords[i]);
        }
        String end = String.join(" ", endWords);

        return new Event(des, start, end);
    }

    @Override
    public String toString() {
        return "[E]" + super.toString() + " (from: " + this.from + " to: " + this.to + ")";
    }
}
