import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

class Deadline extends Task{

    private final LocalDate time;
    public Deadline(String name, LocalDate time) {
        super(name);
        this.time = time;
    }

    @Override
    public String getText() {
        return super.getText() + " | " + time;
    }

    @Override
    public String toString() {
        return "[D]" + super.toString()
                +  " (by: "
                + time.format(DateTimeFormatter.ofPattern("MMM d yyyy"))
                + ")";
    }
}
