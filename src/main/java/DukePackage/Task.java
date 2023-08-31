package DukePackage;

import DukePackage.TaskType;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Objects;

public class Task {
    protected String description;
    protected boolean isDone;
    protected TaskType type;
    protected LocalDateTime start;
    protected LocalDateTime end;
    String markString = "    Nice! I've marked this task as done:";
    String unmarkString = "     OK, I've marked this task as not done yet:";

    public Task(String description, TaskType type, String start, String end) {
        this.description = description;
        this.isDone = false;
        // set to-do as the default type
        this.type = type;
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
        this.start = Objects.equals(start, "")
                ? null
                : LocalDateTime.parse(start, formatter);
        this.end = Objects.equals(end, "")
                ? null
                : LocalDateTime.parse(end, formatter);

    }

    public String getStatusIcon() {
        return (isDone ? "X" : " "); // mark done task with X
    }

    public String getTypeIcon() {
        switch (this.type) {
            case TODO:
                return "T";
            case DEADLINE:
                return "D";
            case EVENT:
                return "E";
        }
        return null;
    }

    public void marking(Boolean checked) {
        this.isDone = checked;
    }

    public void descriptionString() {
        String initStatement = "     Got it. I've added this task:";
        System.out.println(initStatement);
        this.printMarking(false);
    }

    public void printMarking(boolean mark) {
        if (mark) {
            if (this.isDone) {
                System.out.println(markString);
            } else {
                System.out.println(unmarkString);
            }
        }

        System.out.printf("       [%s][%s] %s", this.getTypeIcon(), this.getStatusIcon(), this.description);

        if (!Objects.isNull(this.start) && !Objects.isNull(this.end)) {
            System.out.printf(" (from: %s to: %s)", this.start.toString().replace("T", " "), this.end.toString().replace("T", " "));
        } else if (!Objects.isNull(this.start)) {
            System.out.printf(" (by: %s)", this.start.toString().replace("T", " "));
        } else {
            return;
        }
    }
    //...
}
