import java.util.ArrayList;
import java.util.Arrays;

public class Deadline extends Task {
    protected String by;

    public Deadline(String description, String by) {
        super(description);
        this.by = by;
    }

    public Deadline(String description, String by, boolean isDone) {
        super(description);
        this.by = by;
        this.isDone = isDone;
    }

    /**
     * Generates a Deadline with description given in the input.
     * @param input input entered by user.
     * @return a Deadline.
     */
    public static Deadline generateDeadlineFromInput(String input) throws EmptyDeadlineDescriptionException {
        if (input.split(" ").length == 1) {
            throw new EmptyDeadlineDescriptionException(
                    Action.HORIZONTAL_LINE + "\n" +
                    "You did not provide any description to this Deadline.\n" +
                    "To add a Deadline, enter \"deadline <description> /by <deadline>\".\n" +
                    Action.HORIZONTAL_LINE);
        }

        String front = input.split("/")[0];
        String[] frontWords = front.split(" ");
        ArrayList<String> desWords = new ArrayList<>(Arrays.asList(frontWords).subList(1, frontWords.length));
        String des = String.join(" ", desWords);

        String back = input.split("/")[1];
        String[] backWords = back.split(" ");
        ArrayList<String> ddlWords = new ArrayList<>(Arrays.asList(backWords).subList(1, backWords.length));
        String ddl = String.join(" ", ddlWords);

        return new Deadline(des, ddl);
    }

    @Override
    public String toString() {
        return "[D]" + super.toString() + " (by: " + this.by + ")";
    }
}
