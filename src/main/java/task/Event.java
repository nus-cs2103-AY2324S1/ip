package task;

import java.time.LocalDateTime;

public class Event extends Task {
    private final LocalDateTime start;
    private final LocalDateTime end;

    public Event(String name, String start, String end) {
        super(name);
        this.start = LocalDateTime.parse(start, INPUT_FORMATTER);
        this.end = LocalDateTime.parse(end, INPUT_FORMATTER);
    }

    public Event(String name, String start, String end, Boolean marked) {
        super(name, marked);
        this.start = LocalDateTime.parse(start, INPUT_FORMATTER);
        this.end = LocalDateTime.parse(end, INPUT_FORMATTER);
    }

    @Override
    public Event mark() {
        return new Event(this.name, this.start.format(INPUT_FORMATTER), this.end.format(INPUT_FORMATTER), true);
    }

    @Override
    public Event unmark() {
        return new Event(this.name, this.start.format(INPUT_FORMATTER), this.end.format(INPUT_FORMATTER), false);
    }

    @Override
    public String saveTask() {
        return String.format("E | %s | %s | %s", super.saveTask(),
                this.start.format(INPUT_FORMATTER), this.end.format(INPUT_FORMATTER));
    }

    /**
     * Returns the name of task with start and end times.
     *
     * @return Task.Task to be done
     */
    @Override
    public String toString() {
        return String.format(
                "[E]%s (from: %s to: %s)",
                super.toString(),
                this.start.format(OUTPUT_FORMATTER),
                this.end.format(OUTPUT_FORMATTER));
    }
}
