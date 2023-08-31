package duke.task;
import java.time.DayOfWeek;
import java.time.LocalDate;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;

public class Event extends Task {

    private LocalDate fromDate, toDate;
    private LocalTime fromTime, toTime;
    private DayOfWeek fromDay, toDay;

    public Event(String description, String from, String to) {
        super(description);

        if (from.contains(",")) {
            String [] splitFrom = from.split(",");
            fromDate = super.convertStringToDate(splitFrom[0]);
            fromDay = super.convertStringToDay(splitFrom[1]);
            if (splitFrom.length == 3) {
                fromTime = super.convertStringToTime(splitFrom[2]);
            }
        } else {
            fromDate = super.convertStringToDate(from);
        }

        if (to.contains(",")) {
            String [] splitTo = to.split(",");
            toDate = super.convertStringToDate(splitTo[0]);
            toDay = super.convertStringToDay(splitTo[1]);
            if (splitTo.length == 3) {
                toTime = super.convertStringToTime(splitTo[2]);
            }
        } else {
            toDate = super.convertStringToDate(to);
        }
    }

    public Event(String description, String from, String to, String status) {
        super(description);

        if (from.contains(",")) {
            String [] splitFrom = from.split(", ");
            fromDate = super.convertStringToDate(splitFrom[0]);
            fromDay = super.convertStringToDay(splitFrom[1]);
            if (splitFrom.length == 3) {
                fromTime = super.convertStringToTime(splitFrom[2]);
            }
        } else {
            fromDate = super.convertStringToDate(from);
        }

        if (to.contains(",")) {
            String [] splitTo = to.split(", ");
            toDate = super.convertStringToDate(splitTo[0]);
            toDay = super.convertStringToDay(splitTo[1]);
            if (splitTo.length == 3) {
                toTime = super.convertStringToTime(splitTo[2]);
            }
        } else {
            toDate = super.convertStringToDate(to);
        }

        if(status.equals("Y")){
            super.taskStatusFromFile(true);
        } else {
            super.taskStatusFromFile(false);
        }
    }


    @Override
    public String toString() {

        String fromDate = dateToString(this.fromDate);
        String toDate = dateToString(this.toDate);
        String fromDay = dayToString(this.fromDay);
        String toDay = dayToString(this.toDay);
        String fromTime = timeToString(this.fromTime);
        String toTime = timeToString(this.toTime);

        return "[E]" + super.toString() + " (from:" + fromDate + fromDay + fromTime + " to:" + toDate + toDay + toTime + ")";

    }

    @Override
    public String toFileString(){
        String fromDate = dateToString(this.fromDate);
        String toDate = dateToString(this.toDate);
        String fromDay = dayToString(this.fromDay);
        String toDay = dayToString(this.toDay);
        String fromTime = timeToString(this.fromTime);
        String toTime = timeToString(this.toTime);
        return "E" + super.toFileString() + "|" + fromDate + fromDay + fromTime + "|" + toDate + toDay + toTime;
    }
}
