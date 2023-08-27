import java.time.format.DateTimeParseException;
import java.util.ArrayList;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.temporal.ChronoUnit;

public class Deadline extends Task {

    protected LocalDate by;

    public Deadline(String description, LocalDate by) {
        super(description);
        this.by = by;
    }

    public static void setDeadline(String userOutput, ArrayList<Task> inputList) throws Exception {
        try {
            String newDes = userOutput.split("deadline")[1].split("/by")[0].strip();
            String newBy = userOutput.split("/by")[1].strip();
            LocalDate dateBy = LocalDate.parse(newBy);
            Deadline newDeadline = new Deadline(newDes, dateBy);
            inputList.add(newDeadline);
            System.out.println("Got it. I've added this task:");
            System.out.println(newDeadline);
            System.out.println("Now you have " + inputList.size() + " tasks in the list.");
        } catch (DateTimeParseException e) {
            throw new Exception("Invalid date format.");
        } catch (Exception e) {
            throw new EmptyException("deadline");
        }
    }

    @Override
    public String toString() {
        return "[D]" + super.toString() + " (by: " + by.format(DateTimeFormatter.ofPattern("MMM d yyyy")) + ")";
    }
}