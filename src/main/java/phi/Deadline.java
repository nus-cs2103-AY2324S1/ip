package phi;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

public class Deadline extends Task {
    private String deadlineString;

    public Deadline(String msg, boolean isDone, String deadline) {
        super(Type.D,isDone, msg);
        this.deadlineString = deadline;
        LocalDate deadlineDate;
        DateTimeFormatter inputFormat = DateTimeFormatter.ofPattern("yyyy-MM-dd");
        DateTimeFormatter outputFormat = DateTimeFormatter.ofPattern("MMM dd yyyy");

        try {
            deadlineDate = LocalDate.parse(deadlineString, inputFormat);
            this.deadlineString = deadlineDate.format(outputFormat);
        } catch (DateTimeParseException e) {
            //System.out.println("Can't find a proper date format, using deadline as a String");
        }
    }

    @Override
    public String toString() {
        return super.toString() + String.format(" (by: %s)", deadlineString);
    }

    public static Deadline newDeadline(String input) {
        if (input.equals("deadline")) {
            throw new IllegalArgumentException("You gotta put an actual message in...");
        } else if (!input.startsWith("deadline ")) {
            throw new IllegalArgumentException(
                    String.format("Hey genius, did you mean \"deadline %s\"...", input.substring(8)));
        } else if (!input.contains("/by")) {
            throw new IllegalArgumentException("Look at which moron didn't add a deadline with the \"/by\" flag");
        }
        int byFlag = input.indexOf("/by");
        if (byFlag == 9) {
            throw new IllegalArgumentException("Come on you have to fill in something...");
        } else if (input.endsWith("/by") || input.endsWith("/by ")) {
            throw new IllegalArgumentException("Hey you have to give me a deadline!");
        }

        String taskMsg = input.substring(9, byFlag - 1);
        String deadlineString = input.substring(byFlag + 4);

        return new Deadline(taskMsg, false, deadlineString);
    }

    @Override
    public String outputFormat() {
        return String.format("%s|%b|%s|%s", taskType.toString(), done, taskName, deadlineString);
    }
}