package duke.tasks;

import java.io.Serializable;

public class Deadline extends Task implements Serializable {
    private static final long serialVersionUID = 3L;

    protected String by;

    public Deadline(String description, String by) {
        super(description);
        this.by = by;
    }

    @Override
    public String toString() {
        return String.format("[D]%s (by %s)", super.toString(), by);
    }
}
