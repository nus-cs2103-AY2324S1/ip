package duke.task;
import java.time.DayOfWeek;
import java.time.LocalDate;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.time.temporal.ChronoUnit;


/**
 * The Deadline class represents a task without a specific deadline or time.
 * It inherits from the Task class and provides methods to create and display a Deadline task.
 */
public class Deadline extends Task {

    private LocalDate date;
    private LocalTime time;
    private DayOfWeek day;

    /**
     * Constructs a Deadline task.
     *
     * @param description The description of the Deadline task.
     * @param by The deadline of the Deadline task.
     */
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

    /**
     * Constructs a Deadline task when reloading tasks from storage file.
     *
     * @param description The description of the Deadline task.
     * @param by The deadline of the Deadline task.
     * @param status A string indicating the status of the task ("Y" for done, "N" for not done).
     */
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

    /**
     * Converts the task to a formatted string representation to be displayed to users.
     *
     * @return The formatted string representation of the task.
     */
    @Override
    public String toString() {
        String date = dateToString(this.date);
        String day = dayToString(this.day);
        String time = timeToString(this.time);
        return "[D]" + super.toString() + " (by:" + date + day + time + ")";
    }

    /**
     * Converts the task to a formatted string representation for file storage.
     *
     * @return The formatted string representation of the task for file storage.
     */
    @Override
    public String toFileString(){
        String date = dateToString(this.date);
        String day = dayToString(this.day);
        String time = timeToString(this.time);
        return "D" + super.toFileString() + "|" + date + day + time;
    }
}