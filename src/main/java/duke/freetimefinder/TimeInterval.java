package duke.freetimefinder;

import duke.Ui;

import java.time.LocalDateTime;

public class TimeInterval {
    LocalDateTime from;
    LocalDateTime to;

    public TimeInterval(LocalDateTime from, LocalDateTime to) {
        this.from = from;
        this.to = to;
    }

    @Override
    public String toString() {
        String output = String.format("From: %s", Ui.outputDateTime(from));
        if (this.to == null) {
            return output;
        }
        return output + String.format(" To: %s", Ui.outputDateTime(to));
    }
}
