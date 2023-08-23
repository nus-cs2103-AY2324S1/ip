public class Deadline extends Task {
    String deadline;

    public Deadline(String msg, String deadline) {
        super("D",false, msg);
        this.deadline = deadline;
    }

    @Override
    public String toString() {
        return super.toString() +
                String.format(" (by: %s)", deadline);
    }

    public static Deadline newDeadline(String input) {
        int byFlag = input.indexOf("/by");
        return new Deadline(input.substring(9, byFlag - 1),
                input.substring(byFlag + 4));
    }
}