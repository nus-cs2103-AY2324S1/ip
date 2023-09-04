package HelperClass;
public class Task {
    private boolean isDone;

    private int type;
    private String timePeriod;
    private String taskName;
    public Task(String taskName, int type, String timePeriod, boolean isDone) {
        this.isDone = isDone;
        this.type = type;
        this.timePeriod = timePeriod;
        this.taskName = taskName;

    }

    public void markDone() {
        System.out.println("Nice! I've marked this task as done:");
        isDone = true;
        System.out.println("[X] " + taskName);
    }

    public void unmarkDone() {
        System.out.println("OK, I've marked this task as not done yet:");
        isDone = false;
        System.out.println("[ ] " + taskName);
    }

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

        if (!(this.type == 1)) {
            description = description + " (" + timePeriod + ")";
        }



        return description;
    }

    public String ForRecordingInTextFile() {
        // Record format: "Type | Status | Name | Time"
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

        if (!(this.type == 1)) {
            description = description + " | " + timePeriod;
        }



        return description;
    }
}


