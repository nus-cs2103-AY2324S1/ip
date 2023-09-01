package duck.task;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

import duck.DuckException;
import duck.Parser;

public class DeadlineTask extends Task {
    LocalDate deadline;

    public DeadlineTask(String name, boolean isDone, LocalDate deadline) {
        super(name,  isDone);
        this.deadline = deadline;
    }

    private String formatDeadline() {
        return deadline.format(DateTimeFormatter.ofPattern("MMM dd yyyy"));
    }

    @Override
    public String stringify() {
        String deadlineString = formatDeadline();
        return "D" + super.stringify() + 
                deadlineString.length() + "/" + deadlineString;
    }

    @Override
    public String toString() {
        return "[D]" + super.toString() + " (by: " + formatDeadline()  + ")";
    }

    @Override
    public boolean equals(Object obj) {
        if (obj instanceof DeadlineTask) {
            DeadlineTask other = (DeadlineTask) obj;
            return super.equals(other) && deadline.equals(other.deadline);
        }
        return false;
    }

    
    public static DeadlineTask parse(String fileLine) throws DuckException {

        // Finding isDone
        boolean isDone = fileLine.charAt(1) == '1';

        // Finding name
        int slashIndex = fileLine.indexOf("/");
        int nameLength = Integer.parseInt(fileLine.substring(2, slashIndex));
        String name = fileLine.substring(slashIndex + 1, slashIndex + 1 + nameLength);

        // Finding deadline
        int secondSlashIndex = fileLine.indexOf("/", slashIndex + 1); // The index of the next slash
        String deadlineString = fileLine.substring(secondSlashIndex + 1);
        LocalDate deadline = LocalDate.parse(deadlineString, Parser.fileDateFormatter);

        return new DeadlineTask(name, isDone, deadline);
    }
}