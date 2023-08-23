import java.util.Scanner;

public class Deadline extends Task {

    private String deadlineBy;

    public Deadline(String description, String deadlineBy) {
        super(description);
        this.deadlineBy = deadlineBy;
    }

    @Override
    public String toString() {
        return "[D]" + super.toString() + " (by: " + deadlineBy + ")";
    }
}
