package Duke.Tasks;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
public class Task {
    private Boolean done = false;

    private String task;

    public Task(String input) {
        task = input;
    }

    private String type;

    private String desc;

    private String checkBox;

    private String deadline;

    public void markDone() {
        done = true;
    }


    public void markUndone() {
        done = false;
    }

    protected boolean isDone() {
        return done;
    }

    public String toString() {
        if (done) {
            return "[X] " + "| " + task;
        } else {
            return "[ ] " +"| " + task;
        }
    }

}
