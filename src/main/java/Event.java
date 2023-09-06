import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class Event extends Task{

    private String fromStr;
    private String toStr;
    private LocalDateTime from;
    private LocalDateTime to;

//    public Event(String task, Boolean isNotSaved, String from, String to) {
//        super(task, isNotSaved);
//        this.fromStr = from;
//        this.toStr = to;
//        try {
//            this.from = parseDateTime(from);
//            this.to = parseDateTime(to);
//        } catch (Exception e) {
//            System.out.println(Duke.horizontalLine + "Invalid date format :< Please use dd/MM/yyyy\n" + Duke.horizontalLine);
//        }
//
//        if (isNotSaved) {
//            saveToFile();
//        }
//    }
    public Event(String task, Boolean isNotSaved, String from, String to) throws DukeException {
        super(task, isNotSaved);
        this.fromStr = from;
        this.toStr = to;
        try {
            this.from = parseDateTime(from);
            this.to = parseDateTime(to);
        } catch (Exception e) {
            // Display the error message here
           // System.err.println(Duke.horizontalLine + "Invalid date format :< Please use dd/MM/yyyy\n" + Duke.horizontalLine);
            throw new DukeException(Duke.horizontalLine + "Invalid date format :< Please use dd/MM/yyyy\n" + Duke.horizontalLine);
        }

        if (isNotSaved) {
            saveToFile();
        }
    }


    @Override
    public String toString() {
        String formattedFromDate = from.format(DateTimeFormatter.ofPattern("MMM dd yyyy"));
        String formattedFromTime = from.format(DateTimeFormatter.ofPattern("hh:mma"));
        String formattedToDate = to.format(DateTimeFormatter.ofPattern("MMM dd yyyy"));
        String formattedToTime = to.format(DateTimeFormatter.ofPattern("hh:mma"));

        return "[E]" + super.toString() + " (from: " + formattedFromDate + " " + formattedFromTime
                + " to: " + formattedToDate + " " + formattedToTime + ")";
    }


    public void print() {
        System.out.println(Duke.horizontalLine + "Got it. I've added this task:\n " + this.toString()+ "\n"
                + "Now you have " + Task.getCounter() + " tasks in the list\n" + Duke.horizontalLine);
    }

    public String generateStr() {
        return "E | " + (this.getStatus() == TaskStatus.DONE ? 1 : 0)
                + " | " + this.getTask() + " | " + fromStr + " | " + toStr;
    }

    @Override
    public void saveToFile() {
        Duke.saveTaskToFile(generateStr());
    }

}
