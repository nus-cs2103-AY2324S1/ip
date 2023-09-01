package types;

import java.time.LocalDate;
import java.util.Objects;

public class Party extends Task {
    protected LocalDate from;
    protected LocalDate to;

    public Party(String description, LocalDate from, LocalDate to) {
        super(description);
        this.from = from;
        this.to = to;
    }

    public boolean isToday(LocalDate date) {
        return (Objects.equals(this.from, date));
    }


    @Override
    public String toString() {
        return "{P}" + super.toString() + "~from: " + this.from + ", to: " + this.to + "~";
    }
}
