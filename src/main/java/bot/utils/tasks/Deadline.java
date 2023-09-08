package bot.utils.tasks;

import bot.exceptions.InvalidTaskException;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

/**
 * Deadline subclass. Contains a name and a time the deadline is due.
 */
class Deadline extends Task {
    /**
     * The time the deadline is due.
     */
    private final LocalDate by;

    /**
     * Default constructor.
     *
     * @param name Name of the deadline.
     * @param by   The time the deadline is due.
     */
    public Deadline(String name, LocalDate by) {
        super(name);
        this.by = by;
    }

    /**
     * Alternative constructor. Usually used when reading data from a file.
     *
     * @param name   Name of deadline.
     * @param isDone Completion status of deadline.
     * @param by     The time the deadline is due.
     */
    protected Deadline(String name, boolean isDone, LocalDate by) {
        super(name, isDone);
        this.by = by;
    }

    /**
     * Reads a string of standardised data and constructs a Deadline object based on the information.
     *
     * @param str Data string.
     * @return Deadline object.
     * @throws InvalidTaskException If Deadline object cannot be created.
     */
    public static Deadline convertFromDataString(String str) throws InvalidTaskException {
        if (!str.matches("d/[01]/.+/.+")) {
            throw new InvalidTaskException("Could not read Deadline.");
        }
        String[] arr = str.split("/");
        return new Deadline(arr[2], arr[1].equals("1"),
                LocalDate.parse(arr[3], DateTimeFormatter.ofPattern("yyyy-MM-dd")));
    }

    /**
     * Creates a Deadline object.
     *
     * @param str Raw string to create the Deadline object from.
     * @return Deadline object.
     * @throws InvalidTaskException If a Deadline object cannot be created from the string.
     */
    public static Deadline makeDeadline(String str) throws InvalidTaskException {
        String[] comps = str.split("/");
        if (comps.length != 2) {
            throw new InvalidTaskException("Please make sure the deadline is written in the correct format:\n"
                    + "deadline ... /by ...");
        } else if (comps[0].trim().equals("deadline")) {
            throw new InvalidTaskException("Sorry, the deadline description can't be empty.");
        } else if (comps[1].trim().equals("by")) {
            throw new InvalidTaskException("Sorry, the deadline can't be empty.");
        } else if (!comps[1].startsWith("by")) {
            throw new InvalidTaskException("Please make sure the deadline is written in the correct format:\n"
                    + "deadline ... /by ...");
        }

        LocalDate by;
        try {
            by = LocalDate.parse(comps[1].substring(3).trim());
        } catch (DateTimeParseException e) {
            throw new InvalidTaskException("One or more dates are invalid.");
        }
        if (by.isBefore(LocalDate.now())) {
            throw new InvalidTaskException("Deadline can't be before now!");
        }
        return new Deadline(comps[0].substring(9).trim(), by);
    }

    /**
     * String representation of the deadline.
     *
     * @return String representation.
     */
    @Override
    public String toString() {
        return "[D]" + super.toString() + " (by: "
                + this.getBy().format(DateTimeFormatter.ofPattern("MMM d yyyy"))
                + ")";
    }

    /**
     * Get the deadline time.
     *
     * @return Deadline time.
     */
    protected LocalDate getBy() {
        return this.by;
    }

    /**
     * Returns data string representation.
     *
     * @return Data string.
     */
    public String convertToDataString() {
        return "d/" + (super.isDone() ? "1" : "0") + "/" + super.getName()
                + "/" + this.getBy();
    }

    /**
     * Checks for sameness. Deadlines are the same if they have the same name and deadline.
     *
     * @param o Object to compare to.
     * @return True if objects are the same, else false.
     */
    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        } else if (o instanceof Deadline) {
            Deadline deadline = (Deadline) o;
            return this.getName().equals(deadline.getName())
                    && this.getBy().equals(deadline.getBy());
        }
        return false;
    }
}
