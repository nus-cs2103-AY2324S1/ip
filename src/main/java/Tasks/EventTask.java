package Tasks;

import enums.DukeDateFormats;
import enums.TaskType;

import java.time.LocalDate;

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
                startDate.format(DukeDateFormats.DATE_ONLY.getFormatter()),
                endDate.format(DukeDateFormats.DATE_ONLY.getFormatter()));
    }

    @Override
    public String toString() {
        return TaskType.EVENT.toSymbol() + super.toString() + getEventRange();
    }
}
