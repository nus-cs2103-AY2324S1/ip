import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

public class Deadline extends Task{
    LocalDateTime endTime;

    public Deadline(String deadline) {
        super(deadline);

        int i = deadline.indexOf("/by");
        String end = deadline.substring(i+4);

        try {
            this.endTime = LocalDateTime.parse(end, super.formatter());
        } catch (DateTimeParseException e) {
            throw e;
        }
    }

    @Override
    public String eventCode() {
        return "D";
    }

    @Override
    public String eventDescription() {
        String taskWithoutDeadline = super.eventDescription();  //remove the deadline keyword
        int i = taskWithoutDeadline.indexOf('/');
        return String.format("%s (by: %s)", taskWithoutDeadline.substring(0, i-1), taskWithoutDeadline.substring(i+4));
    }
    @Override
    public String toString() {
        return String.format("[%s][%s] %s (by: %s)",
                this.eventCode(),
                this.getStatusIcon(),
                this.taskWithoutTime(),
                this.taskEndTime()
        );
    }

    @Override
    public String missingDescription() {
        return "☹ OOPS!!! The description of a deadline cannot be empty.";
    }

    @Override
    public String taskWithoutTime() {
        String taskWithoutDeadline = super.eventDescription();
        int i = taskWithoutDeadline.indexOf('/');
        return taskWithoutDeadline.substring(0, i-1);
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
