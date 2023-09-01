package types;

import java.time.LocalDate;
import java.util.Objects;

public class Deadlines extends Task{
    protected LocalDate by;

    public Deadlines(String description, LocalDate by) {
        super(description);
        this.by = by;
    }

    public boolean isToday(LocalDate date) {
        return (Objects.equals(this.by, date));
    }

    @Override
    public String toString() {
        return "[D]" + super.toString() + "[BY: " + this.by + "]";
    }


}
