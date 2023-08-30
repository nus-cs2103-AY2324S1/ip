import java.util.Scanner;

public class Deadline extends Task {

    private String deadlineBy;

    public Deadline(String description, String deadlineBy) {
        super(description);
        this.deadlineBy = deadlineBy;
    }
    public Deadline(int status, String desciption, String deadlineBy) {
        super(desciption, status != 0);     //if 0, return false, else return true
        this.deadlineBy = deadlineBy;
    }

    @Override
    public String storeToDisk() {
        return "D" + "|" + this.getStatus() + "|" + this.getDescription() + "|" + this.deadlineBy;
    }

    @Override
    public String toString() {
        return "[D]" + super.toString() + " (by: " + deadlineBy + ")";
    }
}
