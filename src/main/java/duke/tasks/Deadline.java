package duke.tasks;

public class Deadline extends Task {
    private static final String PRINT_FORMAT = "[D]%s %s (%s)";
    private static final String STORE_FORMAT = "[D] | %s | %s | %s";
    private String by;

    public Deadline(String info, String by) {
        super(info, TaskType.DEADLINE);
        this.by = by;
    }

    @Override
    public String saveString() {
        return String.format(STORE_FORMAT, getFlag(), this.getDescription().trim(), by);
    }

    @Override
    public String toString() {
        return String.format(PRINT_FORMAT, getFlag(), this.getDescription(), by);
    }
}

