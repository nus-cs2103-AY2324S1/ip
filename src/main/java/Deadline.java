import java.util.Arrays;

public class Deadline extends Task{
    String by;
    public Deadline(String name, String by) {
        super(name);
        this.by = by;
    }

    @Override
    public String toString() {
        return "[D]" + super.toString() + " (by: " + this.by + ")";
    }

    @Override
    public String toSaveStateString() {
        String[] state = new String[]{ Command.DEADLINE.getCommand(), this.getDone() ? "1" : "0", this.getTaskName(),
                this.by };
        return String.join(" / ", state);
    }

    public static String[] processInput(String[] splitInput) throws InvalidTaskException {
        splitInput = Task.processInput(splitInput);
        if (splitInput.length < 2) {
            throw new InvalidTaskException("☹ OOPS!!! The description and date of a deadline cannot be empty.");
        }
        String[] deadlineArray = splitInput[1].split(" ");
        splitInput[1] = String.join(" ", Arrays.copyOfRange(deadlineArray, 1, deadlineArray.length));
        return splitInput;
    }
}
