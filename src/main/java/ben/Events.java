package ben;

import java.time.LocalDateTime;

public class Events extends Task {
    private LocalDateTime from;
    private LocalDateTime to;

    public Events(String description, boolean isCompleted, LocalDateTime from, LocalDateTime to) {
        super(description, isCompleted);
        this.from = from;
        this.to = to;
    }

    @Override
    public String toString() {
        return "[E]" + super.toString() + " (from: " + dateFormat(from) + "HRS to: " + dateFormat(to) + "HRS)";
    }

    @Override
    public String saveString() {
        return "E|" + super.saveString() + "|" + saveDateFormat(from) + "|" + saveDateFormat(to);
    }
}
