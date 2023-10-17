package ekud.tasks;

/**
 * Represents the priority level for each task
 */
public enum Priority {
    HIGH("high"),
    MEDIUM("medium"),
    LOW("low");
    private String lvl;
    Priority (String lvl) {
        this.lvl = lvl;
    }
    public static Priority getPriority(String inputLvl) {
        for (Priority priority : Priority.values()) {
            if (priority.lvl.equals(inputLvl)) {
                return priority;
            }
        }
        return null;
    }
}
