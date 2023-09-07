import java.time.LocalDate;

public class Deadline extends Task {
    private static String LINE = "-----------------------------------------\n";
    protected DateTime deadline;

    protected String by;

    public Deadline(String description, String by, boolean isDone) {
        super(description, isDone);
        this.deadline = new DateTime(by);
        this.by = by;
    }

    public static Deadline deadlineParse(String input) {
        int indexBy = input.indexOf("/by");
        if (input.length() < 1) {
            new PotatoException(LINE + "Bruh you wanna do air or something?\n" + LINE);
            return null;
        } else {
            return new Deadline(input.substring(0, indexBy - 1),
                    input.substring(indexBy + 4), false);
        }
    }

    public static Deadline deadlineSavedParse(String[] input) {
        // date given as 19 Sep 2023, cannot be parsed by deadline constructor
        return new Deadline(input[2], input[3], input[1].equals("1"));
    }

    @Override
    public String toSave() {
        return "D | " + (isDone ? "1" : "0") + " | " + description + " | " + by;
    }

    @Override
    public String toString() {
        return "[D]" + super.toString() + " (by: " + deadline.getDate() + ")";
    }
}
