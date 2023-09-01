package duke.task;

import java.time.LocalDate;

/**
 * Event task.
 */
public class Event extends Task {
    private LocalDate from;
    private LocalDate to;

    /**
     * Constructs Event object.
     * @param taskContent
     * @param from
     * @param to
     */
    public Event(String taskContent, LocalDate from, LocalDate to) {
        super(taskContent);
        this.from = from;
        this.to = to;
    }

    @Override
    public String toString() {
        String statusAndTaskContent = super.toString();
        return String.format("  [E] %s (from: %s to: %s)",
                statusAndTaskContent, from.format(formatter), to.format(formatter));
    }

    /**
     * Creates Event task.
     * @param status
     * @param description
     * @param from
     * @param to
     * @return
     */
    public static Event create(String status, String description, String from, String to) {
        Event task = new Event(description, LocalDate.parse(from), LocalDate.parse(to));
        if (status == "1") {
            task.mark();
        }
        return task;
    }

    public String saveToFileLine() {
        return String.format("E | %s | from %s to %s\n", super.saveToFileLine(), from, to);
    }
}
