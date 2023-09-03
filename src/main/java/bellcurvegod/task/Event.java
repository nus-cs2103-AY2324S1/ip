package bellcurvegod.task;

import java.time.LocalDate;
import java.time.format.DateTimeParseException;
import java.util.ArrayList;
import java.util.Arrays;

import bellcurvegod.exception.EmptyEventDescriptionException;
import bellcurvegod.ui.Ui;

/**
 * Encapsulates a task with a duration.
 */
public class Event extends Task {
    protected LocalDate from;
    protected LocalDate to;

    /**
     * Creates an event.
     *
     * @param description Description of event.
     * @param from        From time of event.
     * @param to          To time of event.
     */
    public Event(String description, LocalDate from, LocalDate to) {
        super(description);
        this.from = from;
        this.to = to;
    }

    /**
     * Creates an event.
     *
     * @param description Description of event.
     * @param from        From time of event.
     * @param to          To time of event.
     * @param isDone      Whether the event is marked as done.
     */
    public Event(String description, LocalDate from, LocalDate to, boolean isDone) {
        super(description);
        this.from = from;
        this.to = to;
        this.isDone = isDone;
    }

    /**
     * Generates an Event with description given in the input.
     *
     * @param input Input entered by user.
     * @return An Event.
     * @throws EmptyEventDescriptionException If description is missing.
     */
    public static Event generateEventFromInput(String input) throws EmptyEventDescriptionException {
        if (input.split(" ").length == 1) {
            throw new EmptyEventDescriptionException(
                Ui.getLine() + "\n"
                    + "You did not provide any description to this Event.\n"
                    + "To add an Event, enter \"event <description> /from <startTime> /to <endTime>\".\n"
                    + Ui.getLine());
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

        LocalDate from = null;
        LocalDate to = null;
        try {
            from = LocalDate.parse(start);
            to = LocalDate.parse(end);
        } catch (DateTimeParseException e) {
            Ui.showLine();
            System.out.println("Please enter your times in the following format:");
            System.out.println("yyyy-mm-dd");
            System.out.println("E.g. 2019-10-15");
            Ui.showLine();
            return new Event("__Faulty", from, to);
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
