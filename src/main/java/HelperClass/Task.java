package HelperClass;
public class Task {
    private boolean isDone;

    private int type;
    private String timePeriod;
    private String taskName;
    public Task(String taskName, int type, String timePeriod) {
        this.isDone = false;
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
}


