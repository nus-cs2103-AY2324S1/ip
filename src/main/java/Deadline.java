import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class Deadline extends Task {

    private String byStr;
    private LocalDateTime by;

    public Deadline(String task, Boolean isNotSaved, String by) {
        super(task, isNotSaved);
        this.byStr = by;
        this.by = parseDateTime(by);
        if (isNotSaved) {
            saveToFile();
        }
    }
    
    public String toString() {
        // Format LocalDateTime as a string in your desired output format
        DateTimeFormatter outputFormatter = DateTimeFormatter.ofPattern("MMM dd yyyy h:mm a");
        String formattedDateTime = by.format(outputFormatter);
        return "[D]" + super.toString() + " (by: " + formattedDateTime + ")";
    }

    public void print() {
        System.out.println(Duke.horizontalLine + "Got it. I've added this task:\n " + this.toString()+ "\n"
                + "Now you have " + Task.getCounter() + " tasks in the list\n" + Duke.horizontalLine);
    }

    public String generateStr() {
        return "D | " + (this.getStatus() == TaskStatus.DONE ? 1 : 0)
                + " | " + this.getTask() + " | " + byStr;
    }

    @Override
    public void saveToFile() {
        if (isNotSaved) {
            Duke.saveTaskToFile(generateStr());
        }
    }

}
