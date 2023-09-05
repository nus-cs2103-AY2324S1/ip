package tasks;

import duke.Duke;
import enums.TaskType;

import java.time.LocalDate;
import java.util.Objects;

public class EventTask extends Task {
    private final LocalDate startDate;
    private final LocalDate endDate;

    public EventTask(String description, LocalDate startDate, LocalDate endDate) {
        super(description);
        this.startDate = startDate;
        this.endDate = endDate;
    }

    public String getEventRange() {
        return String.format(" (from: %s to %s)",
                startDate.format(Duke.DATETIME_FORMATTER),
                endDate.format(Duke.DATETIME_FORMATTER));
    }

    @Override
    public String toString() {
        return TaskType.EVENT.toSymbol() + super.toString() + getEventRange();
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof EventTask)) {
            return false;
        }
        if (!super.equals(o)) {
            return false;
        }
        EventTask eventTask = (EventTask) o;
        return this.startDate.equals(eventTask.startDate) && this.endDate.equals(eventTask.endDate);
    }

    @Override
    public int hashCode() {
        return Objects.hash(super.hashCode(), this.startDate, this.endDate);
    }
}
