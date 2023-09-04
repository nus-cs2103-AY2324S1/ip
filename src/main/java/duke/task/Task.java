package duke.task;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class Task {
    //private int num;
    private String details;

    private boolean isMarked;

    private String taskType;

    protected LocalDateTime byTime;

    public Task(String info) {
        //num = index;
        details = info;
        isMarked = false;
        //taskType = type;
    }

    public String getTime() {
        return byTime.format(DateTimeFormatter.ofPattern("E, MMM dd yyyy"));
    }

    public void setMarked() {
        isMarked = true;
    }

    public void setUnmarked() {
        isMarked = false;
    }

    public String showMarked() {
        String output;
        if (isMarked) {
            output = "[X] ";
        } else {
            output = "[ ] ";
        }
        return output;
    }

    public String getDetails() {
        return details;
    }

    public void setTaskType(String type) {
        taskType = type;
    }

    public String getTaskType() {
        return taskType;
    }

    public String saveFormat() {
        String output;
        output = taskType + " / " + showMarked() + "/ " + details;
        return output;
    }

    @Override
    public String toString() {
        String output = "[" + taskType + "]" + showMarked() + details;
        return output;
    }
}
