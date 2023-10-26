package duke.helper;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;


public class Task {
    private boolean isDone;

    private int typeOfTask;

    private String taskName;

    private LocalDate startDate = LocalDate.MAX;
    private LocalDate endDate = LocalDate.MAX;


    public Task(String taskName, int typeOfTask, String startDate, String endDate, boolean isDone) {
        this.taskName = taskName;
        this.typeOfTask = typeOfTask;

        switch (this.typeOfTask) {
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

    public void markDone() {
        isDone = true;
    }

    public void unmarkDone() {
        isDone = false;
    }

    public LocalDate getStartDate() {
        return startDate;
    }

    public LocalDate getEndDate() {
        return endDate;
    }

    public boolean getCompletionStatus() {
        return isDone;
    }

    /**
     * Returns the string representing how the Task object should be displayed.
     * @return String representation of the Task object.
     */
    public String display() {
        String description = "[";
        switch (this.typeOfTask) {
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

        if (this.typeOfTask == 2) {
            description = description + " (by: " + endDate.format(DateTimeFormatter.ofPattern("MM-dd-yyyy")) + ")";
        }

        if (this.typeOfTask == 3) {
            description = description + " (from: " + startDate.format(DateTimeFormatter.ofPattern("MM-dd-yyyy"))
                    + " to: " + endDate.format(DateTimeFormatter.ofPattern("MM-dd-yyyy")) + ")";
        }



        return description;
    }

    /**
     * Returns the string representing how the Task object should be stored.
     * @return String representation of the Task object.
     */
    public String getStringForRecordingInTextFile() {
        // Record format: "typeOfTask | Status | Name | StartTime(optional) | EndTime(optional)"
        // example: "D | 0 | return book | June 6th"
        // "0" for not done and "1" for done

        String description = "";
        switch (this.typeOfTask) {
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

        if (this.typeOfTask == 2) {
            description = description + " | " + endDate.toString();
        }

        if (this.typeOfTask == 3) {
            description = description + " | " + startDate.toString() + " | " + endDate.toString();
        }



        return description;
    }

    public String getTaskName() {
        return taskName;
    }

    public int getTypeOfTask() {
        return typeOfTask;
    }
}


