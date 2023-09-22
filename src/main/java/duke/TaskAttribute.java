package duke;

public enum TaskAttribute {
    description,
    by,
    start,
    end,
    unknown;

    public static TaskAttribute get(String s) {
        if (s == null) {
            return TaskAttribute.unknown;
        }
        try {
            return TaskAttribute.valueOf(s);
        } catch (IllegalArgumentException e) {
            return TaskAttribute.unknown;
        }
    }

}
