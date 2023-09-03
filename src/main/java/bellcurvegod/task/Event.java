package bellcurvegod.task;

import bellcurvegod.exception.EmptyEventDescriptionException;
import bellcurvegod.ui.Ui;

import java.time.LocalDateTime;
import java.time.format.DateTimeParseException;
import java.util.ArrayList;
import java.util.Arrays;

public class Event extends Task {
    protected LocalDateTime from;
    protected LocalDateTime to;

    public Event(String description, LocalDateTime from, LocalDateTime to) {
        super(description);
        this.from = from;
        this.to = to;
    }

    public Event(String description, LocalDateTime from, LocalDateTime to, boolean isDone) {
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
    public static Event generateEventFromInput(String input) throws EmptyEventDescriptionException {
        if (input.split(" ").length == 1) {
            throw new EmptyEventDescriptionException(
                    Ui.getLine() + "\n" +
                    "You did not provide any description to this Event.\n" +
                    "To add an Event, enter \"event <description> /from <startTime> /to <endTime>\".\n" +
                            Ui.getLine());
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

        LocalDateTime from = null;
        LocalDateTime to = null;
        try {
            from = LocalDateTime.parse(start);
            to = LocalDateTime.parse(end);
        } catch (DateTimeParseException e) {
            System.out.println(e);
            System.out.println("Please enter your times in the following format:");
            System.out.println("yyyy-mm-dd hhmm");
            System.out.println("E.g. 2019-10-15 1800");
        }

        return new Event(des, from, to);
    }

    @Override
    public String getDataRepresentation() {
        return "D|" + super.getDataRepresentation() + "|" + this.from + "|" + this.to;
    }

    @Override
    public String toString() {
        return "[E]" + super.toString() + " (from: " + this.from + " to: " + this.to + ")";
    }
}
