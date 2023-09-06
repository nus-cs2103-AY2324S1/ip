import java.time.LocalDate;

public class Deadline extends Task {
    private static String LINE = "-----------------------------------------\n";
    protected DateTime deadline;

<<<<<<< HEAD
    public Deadline(String description, String by, boolean isDone) {
        super(description, isDone);
=======
    public Deadline(String description, String by) {
        super(description);
>>>>>>> 667cac354c5e48fb2e525cddd1a0324068a81297
        this.deadline = new DateTime(by);
    }

    public static Deadline deadlineParse(String input) {
        int indexBy = input.indexOf("/by");
        if (input.length() < 1) {
            new PotatoException(LINE + "Bruh you wanna do air or something?\n" + LINE);
            return null;
        } else {
            return new Deadline(input.substring(0, indexBy),
<<<<<<< HEAD
                    input.substring(indexBy + 4), false);
        }
    }

    public static Deadline deadlineSavedParse(String[] input) {
        // date given as 19 Sep 2023, cannot be parsed by deadline constructor
        return new Deadline(input[2], input[3], input[1].equals("1"));
    }

    @Override
    public String toSave() {
        return "D | " + (isDone ? "1" : "0") + " | " + description + " | " + deadline.getDate();
=======
                    input.substring(indexBy + 4));
        }
    }

    public static Deadline deadlineSavedParse(String input) {
        int indexBy = input.indexOf("by:");
        if (input.length() < 1) {
            new PotatoException(LINE + "Bruh you wanna do air or something?\n" + LINE);
            return null;
        } else {
            return new Deadline(input.substring(0, indexBy),
                    input.substring(indexBy + 4));
        }
>>>>>>> 667cac354c5e48fb2e525cddd1a0324068a81297
    }

    @Override
    public String toString() {
        return "[D]" + super.toString() + " (by: " + deadline.getDate() + ")";
    }
}
