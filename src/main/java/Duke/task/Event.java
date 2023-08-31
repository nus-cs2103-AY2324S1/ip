package Duke.task;

import Duke.exception.DukeException;
import Duke.exception.InvalidTaskFormatException;
import Duke.exception.InvalidTimeFormatException;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.format.DateTimeParseException;

public class Event extends Task {
    final private LocalDateTime startTime;
    final private LocalDateTime endTime;
    public Event(String task) throws DukeException {
        super(task.split("/", 3)[0]);
        String[] taskComponents = task.split("/", 3);
        String[] startTimeComponents;
        String[] endTimeComponents;
        try {
            startTimeComponents = taskComponents[1].split(" ", 3);
            endTimeComponents = taskComponents[2].split(" ", 3);
        } catch (ArrayIndexOutOfBoundsException e) {
            throw new InvalidTaskFormatException(task);
        }
        if (startTimeComponents.length < 3) {
            throw new InvalidTimeFormatException(taskComponents[1]);
        }
        try {
            this.startTime = LocalDateTime.of(LocalDate.parse(startTimeComponents[1]),
                    LocalTime.parse(startTimeComponents[2]));
        } catch (DateTimeParseException e) {
            throw new InvalidTimeFormatException(taskComponents[1]);
        }
        if (endTimeComponents.length < 2) {
            throw new InvalidTimeFormatException(taskComponents[2]);
        }
        try {
            this.endTime = LocalDateTime.of(LocalDate.parse(endTimeComponents[1]),
                    LocalTime.parse(endTimeComponents[2]));
        } catch (DateTimeParseException e) {
            throw new InvalidTimeFormatException(taskComponents[2]);
        }
    }

    public static Event ParseContent(String content) throws DukeException {
        String[] components = content.split("\\|", 4);
        Event task = new Event(components[2] + "/from " + components[0] + "/to " + components[1]);
        if(components[3].equals("X"))
            task.SetCompleted();
        else
            task.SetUncompleted();
        return task;
    }

    public String toString() {
        return "[E]" + super.toString() + " (from: " +
                startTime.toLocalDate().toString() + "," + startTime.toLocalTime().toString() + " to: " +
                endTime.toLocalDate().toString() + "," + endTime.toLocalTime().toString() + ")";
    }
    public String toSaveFormat(){
        return "event:" +
                startTime.toLocalDate().toString() + " " + startTime.toLocalTime().toString() + "|" +
                endTime.toLocalDate().toString() + " " + endTime.toLocalTime() + "|" + super.toSaveFormat();
    }
}
