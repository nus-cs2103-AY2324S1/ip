package Duke;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Arrays;
public class Deadline extends SingleTask {
    String formattedTime;
    LocalDateTime DueBy;
    public Deadline(String description, String deadline) {
        super(description);
        String[] parts = deadline.split("[/ ]");
        int day = Integer.parseInt(parts[0]);
        int month = Integer.parseInt(parts[1]);
        int year = Integer.parseInt(parts[2]);
        int hour = Integer.parseInt(parts[3].substring(0, 2));
        int minute = Integer.parseInt(parts[3].substring(2));
        LocalDateTime dateTime = LocalDateTime.of(year, month, day, hour, minute);
        this.DueBy = dateTime;
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("MMM dd yyyy HH:mm");
        String formattedDateTime = dateTime.format(formatter);
        this.formattedTime = formattedDateTime;

    }

    public void mark() {
        this.isDone = true;
        System.out.println("Ok boy i mark for you already \n" +
                "[" +this.getStatusIcon() +"] " + this.description);

    }
    public String getStatusIcon() {
        return (this.isDone ? "X" : " ");
    }
    public void unmark() {
        this.isDone = false;
        System.out.println("Ok boy I unmark for you already \n" +
                "[" +this.getStatusIcon() +"] " + this.description);
    }

    @Override
    public String toString() {
        return "OK DONE ALR added your Deadline ah:\n" +
                "[D][" + getStatusIcon() + "] " + this.description +"(by: "+ this.formattedTime + ")";
    }
    @Override
    public String listString() {
        return ". [D][" + getStatusIcon() + "] " + this.description +"(by: "+ this.formattedTime + ")";
    }
    @Override
    public String remove() {
        return "OK DONE ALR removed your Deadline ah:\n" +
                "[D][" + getStatusIcon() + "] " + this.description +"(by: "+ this.formattedTime + ")";
    }
    @Override
    public String toSaveString() {
        return "D|" + (this.isDone ? "1" : "0") + "|" + this.description + "|" + this.formattedTime;
    }
}
