package bellcurvegod.task;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Arrays;

import bellcurvegod.exception.EmptyEventDescriptionException;
import bellcurvegod.exception.EmptyFromTimeException;
import bellcurvegod.exception.EmptyToTimeException;
import bellcurvegod.exception.ToTimeEarlierThanFromTimeException;

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
     * @throws EmptyFromTimeException         If from time is missing.
     * @throws EmptyToTimeException           If to time is missing.
     */
    public static Event generateEventFromInput(String input) throws EmptyEventDescriptionException,
            EmptyFromTimeException, EmptyToTimeException, ToTimeEarlierThanFromTimeException {
        if (input.split(" ").length == 1) {
            throw new EmptyEventDescriptionException("You did not provide any description to this Event.\n"
                + "To add an Event, enter \"event <description> /from <startTime> /to <endTime>\".\n");
        }

        String[] wordsSplitBySlash = input.split("/");
        if (wordsSplitBySlash.length == 2) {
            throw new EmptyToTimeException("You did not provide a to date to this Event.\n"
                + "To add an Event, enter \"event <description> /from <startTime> /to <endTime>\".\n");
        }

        String front = wordsSplitBySlash[0];
        if (front.equals(input)) {
            throw new EmptyFromTimeException("You did not provide a from date to this Event.\n"
                + "To add an Event, enter \"event <description> /from <startTime> /to <endTime>\".\n");
        }
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

        LocalDate from = LocalDate.parse(start);
        LocalDate to = LocalDate.parse(end);

        if (from.isAfter(to)) {
            throw new ToTimeEarlierThanFromTimeException("The to time entered is earlier than the from time."
                + "Please check your input.\n");
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
