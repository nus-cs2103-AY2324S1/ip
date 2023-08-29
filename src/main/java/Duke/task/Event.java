package Duke.task;

import Duke.exception.DukeException;
import Duke.exception.InvalidTaskFormatException;
import Duke.exception.InvalidTimeFormatException;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;

public class Event extends Task {
    final private LocalDateTime startTime;
    final private LocalDateTime endTime;
    public Event(String task) throws DukeException {
        super(task.split("/", 3)[0]);
        String[] taskComponents = task.split("/", 3);
        if(taskComponents.length != 3) {
            throw new InvalidTaskFormatException(task);
        }
        String[] startTimeComponents = taskComponents[1].split(" ", 3);
        String[] endTimeComponents = taskComponents[2].split(" ", 3);
        if(startTimeComponents.length < 3) {
            throw new InvalidTimeFormatException(task);
        }
        this.startTime = LocalDateTime.of(LocalDate.parse(startTimeComponents[1].replace('/','-')),
                LocalTime.parse(startTimeComponents[2]));
        if(endTimeComponents.length < 3) {
            throw new InvalidTimeFormatException(task);
        }
        this.endTime = LocalDateTime.of(LocalDate.parse(endTimeComponents[1].replace('/','-')),
                LocalTime.parse(endTimeComponents[2]));
    }

    private Event(String name, String startTime, String endTime) throws DukeException {
        super(name);
        String[] startTimeComponents = startTime.split(",", 2);
        String[] endTimeComponents = endTime.split(",", 2);
        if(startTimeComponents.length < 2) {
            throw new InvalidTimeFormatException(startTime);
        }
        this.startTime = LocalDateTime.of(LocalDate.parse(startTimeComponents[0].replace('/','-')),
                LocalTime.parse(startTimeComponents[1]));
        if(endTimeComponents.length < 2) {
            throw new InvalidTimeFormatException(endTime);
        }
        this.endTime = LocalDateTime.of(LocalDate.parse(endTimeComponents[0].replace('/','-')),
                LocalTime.parse(endTimeComponents[1]));
    }

    public static Event ParseContent(String content) throws DukeException {
        String[] components = content.split("\\|", 4);
        Event task = new Event(components[2], components[0], components[1]);
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
                startTime.toLocalDate().toString() + "," + startTime.toLocalTime().toString() + "|" +
                endTime.toLocalDate().toString() + "," + endTime.toLocalTime() + "|" + super.toSaveFormat();
    }
}
