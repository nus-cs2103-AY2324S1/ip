package task;

public class Task {
    private int num;
    private String details;

    private boolean isMarked;

    public Task(int index, String name) {
        num = index;
        details = name;
        isMarked = false;
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

    @Override
    public String toString() {
        String output = String.format("%d", num) + ". " + showMarked() + details;
        return output;
    }
}
