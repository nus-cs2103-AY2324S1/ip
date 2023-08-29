import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.temporal.ChronoUnit;

class Deadline extends Task {
    private final char taskType = 'D';

    public Deadline(String name) {
        super(name);
    }

    @Override
    public String toString() {
        String checkBox;
        String taskType = String.format("[%c]", this.taskType);
        if (super.isMarked()) {
            checkBox = "[X]";
        } else {
            checkBox = "[ ]";
        }
        return String.format("%s%s %s", taskType, checkBox, super.getName());
    }
}