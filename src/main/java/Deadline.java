public class Deadline extends Task {
    protected String by;
    private static String LINE = "-----------------------------------------\n";
    public Deadline(String description, String by) {
        super(description);
        this.by = by;
    }

    public static Deadline deadlineParse(String input) {
        int indexBy = input.indexOf("/by");
        if (input.length() < 1) {
            new PotatoException(LINE + "Bruh you wanna do air or something?\n" + LINE);
            return null;
        } else {
            return new Deadline(input.substring(0, indexBy),
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
    }

    @Override
    public String toString() {
        return "[D]" + super.toString() + " (by: " + by + ")";
    }
}
