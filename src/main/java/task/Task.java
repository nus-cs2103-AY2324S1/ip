package task;

public class Task {
    //private int num;
    private String details;

    private boolean isMarked;

    private String taskType;

    public Task(String info) {
        //num = index;
        details = info;
        isMarked = false;
        //taskType = type;
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

    public String showDetails() {
        return details;
    }

    public void setTaskType(String type) {
        taskType = type;
    }

    @Override
    public String toString() {
        String output = "[" + taskType + "]" + showMarked() + details;
        return output;
    }
}
