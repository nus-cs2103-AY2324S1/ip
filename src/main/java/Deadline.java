public class Deadline extends Task {
    String deadline;

    public Deadline(String msg, String deadline) {
        super(Type.D,false, msg);
        this.deadline = deadline;
    }

    @Override
    public String toString() {
        return super.toString() +
                String.format(" (by: %s)", deadline);
    }

    public static Deadline newDeadline(String input) {
        if (!input.startsWith("deadline ")) {
            throw new IllegalArgumentException(
                    String.format("Hey genius, did you mean \"deadline %s\"...", input.substring(8)));
        } else if (!input.contains("/by")) {
            throw new IllegalArgumentException("Look at which moron didn't add a deadline with the \"/by\" flag");
        }
        int byFlag = input.indexOf("/by");
        if (byFlag == 9) {
            throw new IllegalArgumentException("Come on you have to fill in something...");
        } else if (input.endsWith("/by")) {
            throw new IllegalArgumentException("Hey you have to give me a deadline!");
        }
        return new Deadline(input.substring(9, byFlag - 1), input.substring(byFlag + 4));
    }
}