package ben;

import java.time.LocalDateTime;

public class Deadlines extends Task {
    private final LocalDateTime by;

    public Deadlines(String description, boolean isCompleted, LocalDateTime by) {
        super(description, isCompleted);
        this.by = by;
    }

    @Override
    public String toString() {
        return "[D]" + super.toString() + " (by: " + dateFormat(by) + "HRS)";
    }

    @Override
    public String saveString() {
        return "D|" + super.saveString() + "|" + saveDateFormat(by);
    }
}
