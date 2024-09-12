package rayshawn.chatbot.tasks;

/**
 * Represents tasks in the task list
 */
public class Task {
    private String description;
    private String type;
    private boolean isDone = false;

    /**
     * Constructor for Task
     *
     * @param description description of task
     * @param type type of task
     */
    public Task(String description, String type) {
        assert description != null : "Description should not be null";
        assert type != null : "Type should not be null";

        this.description = description;
        this.type = type;
    }

    public boolean isDone() {
        return isDone;
    }
    public void markDone() {
        isDone = true;
    }

    public void unmarkDone() {
        isDone = false;
    }

    public String getType() {
        assert type != null : "Type should not be null";
        return type;
    }

    public String getDescription() {
        assert description != null : "Type should not be null";
        return description;
    }

    public void updateDescription(String newDescription) {
        description = newDescription;
    }

    @Override
    public String toString() {
        assert description != null : "Description should not be null";
        assert type != null : "Type should not be null";

        return String.format("[%s][%s] %s", type, isDone ? "X" : " ", description);
    }

}
