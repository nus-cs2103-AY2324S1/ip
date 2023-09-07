package HelperClass;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
//import java.time.temporal.ChronoUnit;


public class Task {
    private boolean isDone;

    private int type;

    private String taskName;

    private LocalDate startDate;
    private LocalDate endDate;


    public Task(String taskName, int type, String startDate, String endDate, boolean isDone) {
        this.taskName = taskName;
        this.type = type;

        switch (this.type) {
        case 1:
            break;
        case 2:
            this.endDate = LocalDate.parse(endDate);
            break;
        case 3:
            this.startDate = LocalDate.parse(startDate);
            this.endDate = LocalDate.parse(endDate);
            break;
        }


        this.isDone = isDone;

    }

    public String markDone() {
        isDone = true;
        String message = "Nice! I've marked this task as done:";
        message = message + "\n" + "[X] " + taskName;
        return message;
    }

    public String unmarkDone() {
        isDone = false;
        String message = "OK, I've marked this task as not done yet:";
        message = message + "\n" + "[ ] " + taskName;
        return message;
    }

    /**
     * return the string representing how the Task object should be displayed
     * @return string representation of the Task object
     */
    public String display() {
        String description = "[";
        switch (this.type) {
        case 1:
            description = description + "T]";
            break;
        case 2:
            description = description + "D]";
            break;
        case 3:
            description = description + "E]";
            break;
        }
        if (isDone) {
            description = description + "[X] " + taskName;
        } else {
            description = description + "[ ] " + taskName;
        }

        if (this.type == 2) {
            description = description + " (by: " + endDate.format(DateTimeFormatter.ofPattern("MMM dd yyyy")) + ")";
        }

        if (this.type == 3) {
            description = description + " (from: " + startDate.format(DateTimeFormatter.ofPattern("MMM dd yyyy"))
                    + " to: " + endDate.format(DateTimeFormatter.ofPattern("MMM dd yyyy")) + ")";
        }



        return description;
    }

    /**
     * return the string representing how the Task object should be stored
     * @return string representation of the Task object
     */
    public String ForRecordingInTextFile() {
        // Record format: "Type | Status | Name | StartTime(optional) | EndTime(optional)"
        // example: "D | 0 | return book | June 6th"
        // "0" for not done and "1" for done

        String description = "";
        switch (this.type) {
        case 1:
            description = description + "T | ";
            break;
        case 2:
            description = description + "D | ";
            break;
        case 3:
            description = description + "E | ";
            break;
        }
        if (isDone) {
            description = description + "1 | " + taskName;
        } else {
            description = description + "0 | " + taskName;
        }

        if (this.type == 2) {
            description = description + " | " + endDate.toString();
        }

        if (this.type == 3) {
            description = description + " | " + startDate.toString() + " | " + endDate.toString();
        }



        return description;
    }

    public String getTaskName() {
        return taskName;
    }
}


