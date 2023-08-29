package rayshawn.chatbot.tasks;

import java.time.DateTimeException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class Deadline extends Task {
    private LocalDate date;
    public Deadline(String task, String date) {
        super(task, "D");
        this.date = LocalDate.parse(date);
    }

   public String getDate() {
        return this.date.format(DateTimeFormatter.ofPattern("yyyy-MM-dd"));
   }

    @Override
    public String toString() {
        return super.toString() + " (by: " + this.date.format(DateTimeFormatter.ofPattern("MMM d yyyy")) + ")";
    }
}
