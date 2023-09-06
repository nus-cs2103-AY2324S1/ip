public class Event extends Task {
    private static String LINE = "-----------------------------------------\n";
    protected DateTime start;
    protected DateTime end;

<<<<<<< HEAD
    public Event(String description, String from, String to, boolean isDone) {
        super(description, isDone);
=======
    public Event(String description, String from, String to) {
        super(description);
>>>>>>> 667cac354c5e48fb2e525cddd1a0324068a81297
        start = new DateTime(from);
        end = new DateTime(to);
    }

    public static Event eventParse(String input) {
        int indexFrom = input.indexOf("/from");
        int indexTo = input.indexOf("/to");
        if (input.length() < 1) {
            new PotatoException(LINE + "Bruh you wanna do air or something?\n" + LINE);
            return null;
        } else {
            return new Event(input.substring(0, indexFrom),
                    input.substring(indexFrom + 6, indexTo),
<<<<<<< HEAD
                    input.substring(indexTo + 4), false);
        }
    }

    public static Event eventSavedParse(String[] input) {
        return new Event(input[2], input[3], input[4], input[1].equals("1"));
    }

    @Override
    public String toSave() {
        return "E | " + (isDone ? "1" : "0") + " | " + description + " | " + start + " | " + end;
=======
                    input.substring(indexTo + 4));
        }
    }

    public static Event eventSavedParse(String input) {
        int indexFrom = input.indexOf("from:");
        int indexTo = input.indexOf("to:");
        if (input.length() < 1) {
            new PotatoException(LINE + "Bruh you wanna do air or something?\n" + LINE);
            return null;
        } else {
            return new Event(input.substring(0, indexFrom),
                    input.substring(indexFrom + 6, indexTo),
                    input.substring(indexTo + 4));
        }
>>>>>>> 667cac354c5e48fb2e525cddd1a0324068a81297
    }

    @Override
    public String toString() {
        return "[E]" + super.toString() + " (from: " + start.getDate() + "to: " + end.getDate() + ")";
    }
}
