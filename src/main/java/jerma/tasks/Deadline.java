package jerma.tasks;

import java.time.LocalDate;

public class Deadline extends Task {
    private LocalDate by;

    public Deadline(String description, String by) {
        super(description);
        this.symbol = "D";
        this.by = LocalDate.parse(by);
    }

    @Override
    public String save() {
        return String.format("%s|%s|%s", this.symbol, super.save(), this.by);
    }

    @Override
    public String toString() {
        return String.format("[%s]%s (by %s)", this.symbol, super.toString(),
                this.by);
    }
}
