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

        String date = this.date.format(DateTimeFormatter.ofPattern("dd MMM yyyy"));
        String day;
        String time;

        if(this.day!=null) {
            day = ", " + this.day.name().substring(0,1) + this.day.name().substring(1).toLowerCase() ;
        } else {
            day = "";
        }

        if(this.time!=null) {
            time = ", " + this.time.format(DateTimeFormatter.ofPattern("h.mma")).toUpperCase();
        } else {
            time = "";
        }
        return "[D]" + super.toString() + " (by:" + date + day + time + ")";
    }

    @Override
    public String toFileString(){

        String date = this.date.format(DateTimeFormatter.ofPattern("dd MMM yyyy"));
        String day;
        String time;

        if(this.day!=null) {
            day = ", " + this.day.name().substring(0,1) + this.day.name().substring(1).toLowerCase() ;
        } else {
            day = "";
        }

        if(this.time!=null) {
            time = ", " + this.time.format(DateTimeFormatter.ofPattern("h.mma")).toUpperCase();
        } else {
            time = "";
        }
        return "D" + super.toFileString() + "|" + date + day + time;
    }
}