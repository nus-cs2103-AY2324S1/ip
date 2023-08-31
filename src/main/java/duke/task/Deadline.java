package duke.task;
import java.time.DayOfWeek;
import java.time.LocalDate;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.time.temporal.ChronoUnit;


public class Deadline extends Task {

    private LocalDate date;
    private LocalTime time;
    private DayOfWeek day;

    public Deadline(String description, String by) {
        super(description);

        if (by.contains(",")) {
            String [] splitBy = by.split(",");
            date = super.convertStringToDate(splitBy[0]);
            day = super.convertStringToDay(splitBy[1]);
            if (splitBy.length == 3) {
                time = super.convertStringToTime(splitBy[2]);
            }
        } else {
            date = super.convertStringToDate(by);
        }
    }

    public Deadline(String description, String by, String status) {
        super(description);

        if (by.contains(",")) {
            String [] splitBy = by.split(", ");
            date = super.convertStringToDate(splitBy[0]);
            day = super.convertStringToDay(splitBy[1]);
            if (splitBy.length == 3) {
                time = super.convertStringToTime(splitBy[2]);
            }
        } else {
            date = super.convertStringToDate(by);
        }

        if(status.contains("Y")){
            super.taskStatusFromFile(true);
        } else {
            super.taskStatusFromFile(false);
        }
    }

    @Override
    public String toString() {
        String date = dateToString(this.date);
        String day = dayToString(this.day);
        String time = timeToString(this.time);
        return "[D]" + super.toString() + " (by:" + date + day + time + ")";
    }

    @Override
    public String toFileString(){
        String date = dateToString(this.date);
        String day = dayToString(this.day);
        String time = timeToString(this.time);
        return "D" + super.toFileString() + "|" + date + day + time;
    }
}