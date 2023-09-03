package Tasks;

import enums.DukeDateFormats;
import enums.TaskType;

import java.time.LocalDate;

public class DeadlineTask extends Task {
    private final LocalDate endDate;

    public DeadlineTask(String description, LocalDate endDate) {
        super(description);
        this.endDate = endDate;
    }

    public String getDeadline() {
        return String.format(" (by: %s)", endDate.format(DukeDateFormats.DATE_ONLY.getFormatter()));
    }

    @Override
    public String toString() {
        return TaskType.DEADLINE.toSymbol() + super.toString() + getDeadline();
    }
}
