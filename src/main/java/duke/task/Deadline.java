package duke.task;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
/**
 * Deadline task.
 */
public class Deadline extends Task{
//    private String due;
    private LocalDate due;

    public Deadline(String taskContent, LocalDate due) {

        super(taskContent);
        this.due = due;
    }

    @Override
    public String toString() {
        String statusAndTaskContent = super.toString();
        return String.format("  [D] %s (by: %s)", statusAndTaskContent, due.format(formatter));
    }

    public static Deadline create(String status, String description, String due) {
        Deadline task = new Deadline(description, LocalDate.parse(due));
        if (status == "1") {
            task.mark();
        }
        return task;
    }

    public String saveToFileLine() {
        return String.format("D | %s | %s\n", super.saveToFileLine(), due.toString());
    }
}
