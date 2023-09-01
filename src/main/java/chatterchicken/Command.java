package chatterchicken;

public class Command {
    private final String action;
    private final String taskDescription;

    public Command(String action, String taskDescription) {
        this.action = action;
        this.taskDescription = taskDescription;
    }

    public String getAction() {
        return action;
    }

    public String getTaskDescription() {
        return taskDescription;
    }
}
