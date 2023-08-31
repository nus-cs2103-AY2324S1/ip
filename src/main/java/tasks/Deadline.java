package tasks;

import java.util.Optional;
import java.time.LocalTime;
import java.time.LocalDate;

public class Deadline extends TaskAbstract {
    protected LocalDate date;
    protected Optional<LocalTime> time;

    public Deadline(String description, String deadline) {
        super(description);
        String[] delimited = deadline.split(" ");
        String endDate = delimited[0];
        String year = endDate.substring(0, 4);
        String month = endDate.substring(5, 7);
        String day = endDate.substring(8, 10);
        this.date = LocalDate.parse(String.format("%s-%s-%s", year, month, day));
        if (delimited.length > 1) {
            this.time = Optional.<LocalTime>of(LocalTime.parse(delimited[1]));
        } else {
            this.time = Optional.<LocalTime>empty();
        }
    }

    private String getDateTime() {
        return this.date.toString() + (this.time.isEmpty() ? "" : " " + this.time.get().toString());
    }

    public String saveToTextFormat() {
        return String.format("D | %s | %s | %s", this.isDone ? "1" : "0", this.description, this.getDateTime());
    }

    @Override
    public void printStatus() {
        System.out.printf("[D][%s] %s (by: %s)\n", this.isDone ? "X" : " ", this.description, this.getDateTime());
    }
}
