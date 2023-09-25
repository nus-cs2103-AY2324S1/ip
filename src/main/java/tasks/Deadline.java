package tasks;

import functional.DukeException;

import java.time.DateTimeException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

public class Deadline extends Task implements Comparable<Task> {
    private String[] splitSlash;
    private String[] split2;
    private LocalDate dueDate;
    private Integer timeCompare;
    private String name;
    private boolean isRecurring;

    /**
     * Constructs a new Deadline task with the user command and 'mark' status
     *
     * @param content input given by the user
     * @param status  marked or unmarked
     */
    public Deadline(String content, boolean status) throws DukeException {
        super(content, status);
        splitSlash = content.split("/", 2);
        split2 = splitSlash[1].split(" ", 3);
        timeCompare = Integer.parseInt(split2[2]);
        this.isRecurring = false;
        String[] title = splitSlash[0].split(" ", 3);
        if (title[1].equals("recurring")) {
            this.isRecurring = true;
            name = title[2];
        } else {
            name = title[1] + " " + title[2];
        }
        try {
            this.dueDate = LocalDate.parse(split2[1].replace("/", "-"));
        } catch (DateTimeParseException e) {
            try {
                DateTimeFormatter formatter = DateTimeFormatter.ofPattern("d/MM/yyyy");
                this.dueDate = LocalDate.parse(split2[1], formatter);
            } catch (DateTimeException ex) {
                throw new DukeException("\n" + "DateTime wrong input format\n");
            }
        }
    }

    public Deadline update(LocalDate newLocalDate) throws DukeException {
        String newContent = String.format("%s/%s %s %s", splitSlash[0], split2[0],
                newLocalDate.toString(), split2[2]);
        return new Deadline(newContent, super.isMarked());
    }

    /**
     * @return The deadline object but marked [X]
     */
    public Deadline mark() throws DukeException {
        return new Deadline(super.getContent(), true);
    }

    /**
     * @return The Deadline object but unmarked [ ]
     */
    public Deadline unmark() throws DukeException {
        return new Deadline(super.getContent(), false);
    }

    /**
     * @param listSize
     * @return a string that contains the message printed when a task is added to TaskList
     */
    public String addTask(int listSize) {
        return "____________________________________________________________\n"
                + "Got it. I've added this task:\n"
                + toString() + "\n"
                + String.format("Now you have %d tasks in the list\n", listSize)
                + "____________________________________________________________";
    }

    public LocalDate getDateTime() {
        return dueDate;
    }

    public Integer getTimeCompare() {
        return timeCompare;
    }

    public boolean isRecurring() {
        return this.isRecurring;
    }

    /**
     * @return The status of this task through a string
     */
    public String toString() {
        String dueDateString = dueDate.format(DateTimeFormatter.ofPattern("MMM d yyyy"));
        if (!super.isMarked()) {
            return String.format("[D][ ] %s(by: %s %s)", name, dueDateString, split2[2]);
        } else {
            return String.format("[D][X] %s(by: %s %s)", name, dueDateString, split2[2]);
        }
    }
}
