package blip.priority;

public enum Priority {
    LOW,
    MEDIUM,
    HIGH;

    public static Priority convertToPriority(String priority) {
        String priorityLowerCase = priority.toLowerCase();
        switch (priorityLowerCase) {
            case "low":
                return LOW;
            case "medium":
                return MEDIUM;
            case "high":
                return HIGH;
            default:
                return MEDIUM;
        }
    }
}
