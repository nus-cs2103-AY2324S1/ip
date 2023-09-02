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

    /**
     * Parses the input and returns the appropriate command if the input is
     * valid.
     * 
     * @param input User's input
     * @return Command that tells what the chatbot should do. 
     * @return null if the input in invalid
     */
    public static TaskType parseInput(String input) {
        for(TaskType task: TaskType.values()) {
            if (task.input.equals(input)) {
                return task;
            }
        }
        return null;
    }
}
