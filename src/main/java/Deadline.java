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

    public static String[] processInput(String[] splitInput) {
        splitInput = Task.processInput(splitInput);
        String[] deadlineArray = splitInput[1].split(" ");
        splitInput[1] = String.join(" ", Arrays.copyOfRange(deadlineArray, 1, deadlineArray.length));
        return splitInput;
    }
}
