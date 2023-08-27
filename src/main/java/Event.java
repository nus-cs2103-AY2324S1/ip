import java.util.Arrays;

public class Event extends Task{
    String from;
    String to;

    public Event(String name, String from, String to) {
        super(name);
        this.from = from;
        this.to = to;
    }

    @Override
    public String toString() {
        return "[E]" + super.toString() + " (from: " + this.from + " to: " + this.to + ")";
    }

    @Override
    public String toSaveStateString() {
        String[] state = new String[]{ Command.TODO.getCommand(), this.getDone() ? "1" : "0", this.getTaskName(),
                this.from, this.to };
        return String.join(" / ", state);
    }

    public static String[] processInput(String[] splitInput) throws InvalidTaskException {
        splitInput = Task.processInput(splitInput);
        if (splitInput.length < 3) {
            throw new InvalidTaskException("☹ OOPS!!! The description, start and end of a event cannot be empty.");
        }
        String[] startArray = splitInput[1].split(" ");
        splitInput[1] = String.join(" ", Arrays.copyOfRange(startArray, 1, startArray.length));
        String[] endArray = splitInput[2].split(" ");
        splitInput[2] = String.join(" ", Arrays.copyOfRange(endArray, 1, endArray.length));
        return splitInput;
    }
}
