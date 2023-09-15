package duke.tasks;

import java.time.LocalDateTime;

public interface Temporal {

    LocalDateTime getStartTime();
    LocalDateTime getEndTime();
    boolean isWithinPeriod(LocalDateTime start, LocalDateTime end);

}
