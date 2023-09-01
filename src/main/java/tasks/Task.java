package tasks;

public class Task {
    public String name;
    public boolean isComplete;

    public Task(String name) {
        this.name = name;
        this.isComplete = false;
    }

    public String getMark() {
        return (isComplete ? "X" : " ");
    }

    public String toString() {
        return "[" + getMark() + "] " + name;
    }

    public String taskToStringStore(Task task) {
        String isCompleteString = (getMark() == "X") ? "X" : "O";
        return isCompleteString + "-" + task.name + "-";
    }

}
