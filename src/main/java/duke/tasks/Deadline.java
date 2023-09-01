package duke.tasks;

import java.io.Serializable;
import java.time.LocalDateTime;

public class Deadline extends Task implements Serializable {
    private static final long serialVersionUID = 3L;
    protected LocalDateTime by;

    public Deadline(String description, LocalDateTime by) {
        super(description);
        this.by = by;
    }

    @Override
    public String toString() {
        return String.format("[D]%s (by %s)", super.toString(), by);
    }
}
