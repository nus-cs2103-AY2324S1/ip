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

    public static String[] processInput(String[] splitInput) {
        splitInput = Deadline.processInput(splitInput);
        String[] deadlineArray = splitInput[2].split(" ");
        splitInput[2] = String.join(" ", Arrays.copyOfRange(deadlineArray, 1, deadlineArray.length));
        return splitInput;
    }
}
