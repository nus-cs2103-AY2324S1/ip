package duke.task;

import java.io.IOException;
import java.time.format.DateTimeParseException;
import java.util.ArrayList;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import duke.DukeException;

public class Event extends Task{
    private String description;
    private LocalDate from;
    private LocalDate to;

    public Event(String description, LocalDate from, LocalDate to) {
        super(description);
        this.from = from;
        this.to = to;
        this.description = description;
    }

    @Override
    public String toString() {
        return "[E]" + super.toString() +
                "(from: " + from.format(DateTimeFormatter.ofPattern("MMM d yyyy"))
                + " to: " + to.format(DateTimeFormatter.ofPattern("MMM d yyyy")) + ")";
    }
    public String toStringFile() {
        return "E | " + super.toStringFile() + "/from " + from + "/to " + to;
    }
    @Override
    public String getType() { return "Event"; }


    public static Event addEvent(String description, ArrayList<Task> list) throws DukeException {
        String[] event = description.stripTrailing().split("/from |/to ");
        if (event[0].isEmpty()) {
            throw new DukeException("☹ OOPS!!! The description of an Event cannot be empty.");
        }
        if (event.length < 3) {
            throw new DukeException("☹ OOPS!!! Please provide a valid start and end date");
        }

        Event newTask = null;
        try {
            LocalDate start = LocalDate.parse(event[1].stripTrailing());
            LocalDate end = LocalDate.parse(event[2].stripTrailing());
            if (start.isAfter(end)) {
                throw new DukeException("☹ OOPS!!! Your start date has to be before your end date!");
            }
            newTask = new Event(event[0], start, end);

            list.add(newTask);
        } catch (DateTimeParseException e) {
            System.out.println("Your date should be formatted as YYYY-MM-DD");
        }

        return newTask;


    }
    public static void addSavedEvent(String description, ArrayList<Task> list, String isMarked) {
        String[] event = description.stripTrailing().split("/from |/to ");
        try {
            Event newTask = new Event(event[0], LocalDate.parse(event[1]), LocalDate.parse(event[2]));
            list.add(newTask);
            newTask.markFromRead(isMarked);
        } catch (DateTimeParseException e) {
            System.out.println("Please enter a valid Date!");
        }

    }

    @Override
    public boolean equals(Object obj) {
        if (obj instanceof Event) {
            if (obj == this) {
                return true;
            }
            Event event = (Event) obj;
            return this.description.equals(event.description) &&
                    this.to.equals(event.to) && this.from.equals(event.from);
        }
        return false;
    }
}
