package tasks;

import functional.DukeException;

import java.time.DateTimeException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

public class Event extends Task implements Comparable<Task> {
    private String[] splitSlash;
    private String[] startDate;
    private String[] endTime;
    private String startTime;
    private LocalDate localDate;

    /**
     * Constructs a new Event task with the specified user input.
     *
     * @param content The user input to create an event
     * @param status  marked or unmarked
     */
    public Event(String content, boolean status) throws DukeException {
        super(content, status);
        splitSlash = content.split("/", 2);
        startDate = splitSlash[1].split(" ", 3);
        String[] split2 = startDate[2].split("/", 2);
        startTime = split2[0];
        endTime = split2[1].split(" ", 2);
        try {
            this.localDate = LocalDate.parse(startDate[1].replace("/", "-"));
        } catch (DateTimeParseException e) {
            try {
                DateTimeFormatter formatter = DateTimeFormatter.ofPattern("d/MM/yyyy");
                this.localDate = LocalDate.parse(startDate[1], formatter);
            } catch (DateTimeException ex) {
                this.startTime = startDate[1] + " " + split2[0];
            }
        }
    }

    /**
     * @return the event as marked [X]
     */
    public Event mark() throws DukeException {
        return new Event(super.getContent(), true);
    }

    /**
     * @return the event as unmarked [ ]
     */
    public Event unmark() throws DukeException {
        return new Event(super.getContent(), false);
    }

    /**
     * @param listSize size of the current TaskList
     * @return a String consisting of a message displayed when adding an event to TaskList
     */
    public String addTask(int listSize) {
        return "____________________________________________________________\n"
                + "Got it. I've added this task:\n"
                + toString() + "\n"
                + String.format("Now you have %d tasks in the list,\n", listSize)
                + "____________________________________________________________";
    }

    public LocalDate getDateTime() {
        return localDate;
    }

    public Integer getTimeCompare() {
        return Integer.parseInt(startTime.substring(0, startTime.length() - 1));
    }

    /**
     * @return a String message describing the event
     */
    public String toString() {
        String[] result = splitSlash[0].split(" ", 2);
        String startDateString = localDate.format(DateTimeFormatter.ofPattern("MMM d yyyy")) + " " + startTime;
        if (!super.isMarked()) {
            return String.format("[E][ ] %s(%s: %s%s: %s)", result[1], startDate[0], startDateString, endTime[0], endTime[1]);
        } else {
            return String.format("[E][X] %s(%s: %s%s: %s)", result[1], startDate[0], startDateString, endTime[0], endTime[1]);
        }
    }
}
