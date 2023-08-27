import java.time.LocalDateTime;
import java.time.chrono.ChronoLocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

public class Event extends Task{
    LocalDateTime startTime;
    LocalDateTime endTime;

    public Event(String event) {
        super(event);
        int i = event.indexOf("/from");
        int j = event.indexOf("/to");

        String start = event.substring(i+6, j-1);
        String end = event.substring(j+4);

        try {
            this.startTime = LocalDateTime.parse(start, super.formatter());
            this.endTime = LocalDateTime.parse(end, super.formatter());

        } catch (DateTimeParseException e) {
            throw e;
        }
    }
    @Override
    public String eventCode() {
        return "E";
    }

    @Override
    public String eventDescription() {
        String taskWithoutDates = super.eventDescription(); //remove the event keyword
        int i = taskWithoutDates.indexOf('/');
        int j = taskWithoutDates.indexOf("/to");


        return String.format("%s (from: %s to: %s)",
                taskWithoutDates.substring(0, i-1),
                taskWithoutDates.substring(i+6, j -1),
                taskWithoutDates.substring(j+4)
                );
    }

    @Override
    public String toString() {
        return String.format("[%s][%s] %s (from: %s to: %s)",
                this.eventCode(),
                this.getStatusIcon(),
                this.taskWithoutTime(),
                this.taskStartTime(),
                this.taskEndTime()
        );
    }

    @Override
    public String missingDescription() {
        return "☹ OOPS!!! The description of an event cannot be empty.";
    }

    @Override
    public String taskWithoutTime() {
        String taskWithoutDeadline = super.eventDescription();
        int i = taskWithoutDeadline.indexOf('/');
        return taskWithoutDeadline.substring(0, i-1);
    }

    @Override
    public String taskStartTime() {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd yyyy HH:mm");
        //format : MMM dd yyyy HH:mm ({dayOfWeek})
        return String.format(
                "%s %s (%s)",
                this.startTime.getMonth().toString(),
                this.startTime.format(formatter),
                this.startTime.getDayOfWeek()
        );
    }

    @Override
    public String taskEndTime() {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd yyyy HH:mm");
        //format : MMM dd yyyy HH:mm ({dayOfWeek})
        return String.format(
                "%s %s (%s)",
                this.endTime.getMonth().toString(),
                this.endTime.format(formatter),
                this.endTime.getDayOfWeek()
        );

    }

}
