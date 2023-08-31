package Duke.task;

import Duke.exception.DukeException;
import Duke.exception.InvalidTaskFormatException;
import Duke.exception.InvalidTimeFormatException;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.format.DateTimeParseException;

public class Deadline extends Task{
    final private LocalDateTime deadlineTime;

    public Deadline(String task) throws DukeException {
        super(task.split("/",2)[0]);
        String[] taskComponents = task.split("/",2);
        String[] timeComponents;
        try {
            timeComponents = taskComponents[1].split(" ", 3);
            if (timeComponents.length < 3) {
                throw new InvalidTimeFormatException(taskComponents[1]);
            }
        } catch (ArrayIndexOutOfBoundsException e) {
            throw new InvalidTaskFormatException(task);
        }
        try {
            deadlineTime = LocalDateTime.of(LocalDate.parse(timeComponents[1]),
                    LocalTime.parse(timeComponents[2]));
        } catch (DateTimeParseException e) {
            throw new InvalidTimeFormatException(taskComponents[1]);
        }
    }
    public static Deadline ParseContent(String content) throws DukeException {
        String[] components = content.split("\\|", 3);
        Deadline task = new Deadline(components[1] + "/by" + components[0]);
        if(components[2].equals("X"))
            task.SetCompleted();
        else
            task.SetUncompleted();
        return task;
    }

    public String toString() {
        return "[D]" + super.toString() + " (by: " + deadlineTime.toLocalDate().toString() + ","
                + deadlineTime.toLocalTime().toString() + ")";
    }
    public String toSaveFormat(){
        return "deadline:" + deadlineTime.toLocalDate().toString() + " "
                + deadlineTime.toLocalTime().toString() + "|" + super.toSaveFormat();
    }
}
