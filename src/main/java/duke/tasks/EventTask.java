package duke.tasks;

import duke.ui.Ui;

import java.time.LocalDateTime;

public class EventTask extends Task {
    private LocalDateTime from;
    private LocalDateTime to;

    public EventTask(String name, LocalDateTime from, LocalDateTime to) {
        super(name);
        this.from = from;
        this.to = to;
    }

    @Override
    public String getDescription() {
        return "[E]" + super.getDescription() + " (from: " + from.format(Ui.DATE_OUTPUT_FORMAT) + " to: " + to.format(Ui.DATE_OUTPUT_FORMAT) + ")";
    }

    @Override
    public String toFileString() {
        return "E | " + super.toFileString() + " | " + from.format(Ui.DATE_OUTPUT_FORMAT) + " | " + to.format(Ui.DATE_OUTPUT_FORMAT);
    }

    @Override
    public String toString() {
        return super.toString() + " | " + from.format(Ui.DATE_OUTPUT_FORMAT) + " | " + to.format(Ui.DATE_OUTPUT_FORMAT);
    }
}
