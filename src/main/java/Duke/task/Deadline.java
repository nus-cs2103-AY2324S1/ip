package Duke.task;

import Duke.exception.DukeException;
import Duke.exception.EmptyTaskDescException;
import Duke.exception.InvalidTaskFormatException;
import Duke.exception.InvalidTimeFormatException;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;

public class Deadline extends Task{
    final private LocalDateTime deadlineTime;

    public Deadline(String task) throws DukeException {
        super(task.split("/", 2)[0]);
        String[] taskComponents = task.split("/",2);
        if(taskComponents.length != 2) {
            throw new InvalidTaskFormatException(task);
        }
        String[] timeComponents = taskComponents[1].split(" ", 3);
        if(timeComponents.length < 2) {
            throw new InvalidTimeFormatException(task);
        }
        this.deadlineTime = LocalDateTime.of(LocalDate.parse(timeComponents[1]),
                                              LocalTime.parse(timeComponents[2]));
    }

    private Deadline(String name, String time) throws EmptyTaskDescException {
        super(name);
        String[] timeComponents = time.split(",", 3);
        if(timeComponents.length < 3) {
            deadlineTime = LocalDateTime.of(LocalDate.parse(timeComponents[0]),
                           LocalTime.parse(timeComponents[1]));
        } else {
            deadlineTime = LocalDateTime.of(LocalDate.parse(timeComponents[0]),
                    LocalTime.parse(timeComponents[1]));
        }
    }

    public static Deadline ParseContent(String content) throws EmptyTaskDescException {
        String[] components = content.split("\\|", 3);
        Deadline task = new Deadline(components[1], components[0]);
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
        return "deadline:" + deadlineTime.toLocalDate().toString() + ","
                + deadlineTime.toLocalTime().toString() + "|" + super.toSaveFormat();
    }
}
