package chatbot.task;

/**
 * Enum representing the different types of tasks.
 */
public enum TaskType {
    TODO("T"),
    DEADLINE("D"),
    EVENT("E");

    private String input;

    private TaskType(String input) {
        this.input = input;
    }
}
