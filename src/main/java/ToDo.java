/**
 * Encapsulates a Todo in the chat bot.
 * Tasks without any date/time attached.
 *
 * @author Rayson
 */
public class ToDo extends Task {

    public ToDo(String description) {
        super(description);
    }

    @Override
    public String toString() {
        return String.format("[T]%s", super.toString());
    }

}
