package duke.task;

/**
 * Represents the priority levels of tasks in Duke.
 */
public enum TaskPriority {
    HIGH,
    MEDIUM,
    LOW;

    /**
     * Parses a string input to return the corresponding TaskPriority value.
     *
     * @param input The string input representing the TaskPriority.
     * @return The TaskPriority enum value.
     * @throws IllegalArgumentException If the input is not a valid TaskPriority.
     */
    public static TaskPriority parse(String input) {
        switch (input.toUpperCase()) {
        case "H":
        case "HIGH":
            return HIGH;
        case "M":
        case "MEDIUM":
            return MEDIUM;
        case "L":
        case "LOW":
            return LOW;
        default:
            throw new IllegalArgumentException("Invalid priority input: " + input);
        }
    }

    /**
     * Converts the TaskPriority to its code representation.
     *
     * @return The code representation as a string ("H" for HIGH, "M" for MEDIUM, "L" for LOW).
     * @throws IllegalArgumentException If the TaskPriority is invalid.
     */
    public String toCode() {
        switch (this) {
        case HIGH:
            return "H";
        case MEDIUM:
            return "M";
        case LOW:
            return "L";
        default:
            throw new IllegalArgumentException("Invalid TaskPriority: " + this.name());
        }
    }

}
