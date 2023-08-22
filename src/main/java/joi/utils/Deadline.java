package joi.utils;

public class Deadline extends Task {
    private final String end;
    public Deadline (String description) throws InvalidCommandException {
        super();

        if (description.length() <= 9) {
            throw new InvalidCommandException("Deadline has to have an end date.");
        }
        description = description.substring(9);

        String[] tokens = description.split(" /by ");
        if (tokens.length != 2) {
            throw new InvalidCommandException("Deadline has to have an end date.");
        }
        this.description = tokens[0];
        this.end = tokens[1];
    }

    @Override
    public String toString() {
        return "[D]" + super.toString() + " (by: " + this.end + ")";
    }
}
