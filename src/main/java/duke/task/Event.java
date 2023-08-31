package duke.task;

import java.time.format.DateTimeParseException;
import java.util.ArrayList;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import duke.DukeException;


/**
 * Represents a task with a start and end date.
 * */
public class Event extends Task{
    private String description;
    private LocalDate from;
    private LocalDate to;


    /**
     * Constructs an Event task.
     *
     * @param description The description of the task.
     * @param from        The start date of the event.
     * @param to          The end date of the event.
     */
    public Event(String description, LocalDate from, LocalDate to) {
        super(description);
        this.from = from;
        this.to = to;
        this.description = description;
    }

    /**
     * Returns a formatted string representation of the Event task.
     *
     * @return The formatted string representation.
     */
    @Override
    public String toString() {
        return "[E]" + super.toString() +
                "(from: " + from.format(DateTimeFormatter.ofPattern("MMM d yyyy"))
                + " to: " + to.format(DateTimeFormatter.ofPattern("MMM d yyyy")) + ")";
    }

    /**
     * Returns a formatted string representation of the Event task for file storage.
     *
     * @return The formatted string representation for file storage.
     */
    public String toStringFile() {
        return "E | " + super.toStringFile() + "/from " + from + "/to " + to;
    }

    /**
     * Returns the type of the task.
     *
     * @return The type of the task ("Event").
     */
    @Override
    public String getType() {
        return "Event";
    }

    /**
     * Adds a new Event task to the list based on the description provided.
     *
     * @param description The description of the task and its start and end dates.
     * @param list        The ArrayList of tasks to add the new task to.
     * @return The newly added Event task.
     * @throws DukeException If there is an issue adding the task.
     */
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

    /**
     * Adds an Event task to the list based on the saved description and mark status.
     *
     * @param description The saved description of the task and its start and end dates.
     * @param list        The ArrayList of tasks to add the saved task to.
     * @param isMarked    The mark status of the saved task ("1" for marked, "0" for unmarked).
     */
    public static void addEventFromFile(String description, ArrayList<Task> list, String isMarked) {
        String[] event = description.stripTrailing().split("/from |/to ");
        try {
            Event newTask = new Event(event[0], LocalDate.parse(event[1]), LocalDate.parse(event[2]));
            list.add(newTask);
            newTask.markFromRead(isMarked);
        } catch (DateTimeParseException e) {
            System.out.println("Please enter a valid Date!");
        }
    }

    /**
     * Compares this Event task with another object for equality.
     *
     * @param obj The object to compare with.
     * @return True if the objects are equal, false otherwise.
     */
    @Override
    public boolean equals(Object obj) {
        if (obj instanceof Event event) {
            if (obj == this) {
                return true;
            }
            return this.description.equals(event.description) &&
                    this.to.equals(event.to) && this.from.equals(event.from);
        }
        return false;
    }
}
