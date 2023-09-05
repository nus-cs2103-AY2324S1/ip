package tasks;

import duke.Duke;
import enums.TaskType;

import java.time.LocalDate;
import java.util.Objects;

public class DeadlineTask extends Task {
    private final LocalDate endDate;

    public DeadlineTask(String description, LocalDate endDate) {
        super(description);
        this.endDate = endDate;
    }

    public String getDeadline() {
        return String.format(" (by: %s)", endDate.format(Duke.DATETIME_FORMATTER));
    }

    @Override
    public String toString() {
        return TaskType.DEADLINE.toSymbol() + super.toString() + getDeadline();
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof DeadlineTask)) {
            return false;
        }
        if (!super.equals(o)) {
            return false;
        }
        DeadlineTask otherDeadlineTask = (DeadlineTask) o;
        return this.endDate.equals(otherDeadlineTask.endDate);
    }

    @Override
    public int hashCode() {
        return Objects.hash(super.hashCode(), this.endDate);
    }
}
