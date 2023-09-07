package chatterchicken.command;

/**
 * The Command class represents a command provided by the user for the ChatterChicken task manager application.
 * It encapsulates both the action and task description specified by the user.
 */
public class Command {
    private final String action;
    private final String taskDescription;

    /**
     * Constructs a new Command with the specified action and task description.
     *
     * @param action The action to be performed, such as adding, marking, unmarking, or deleting a task.
     * @param taskDescription The description or details associated with the command, typically used for task creation.
     */
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
