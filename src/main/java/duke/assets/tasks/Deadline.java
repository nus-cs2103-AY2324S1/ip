package duke.assets.tasks;

import java.time.LocalDate;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.time.temporal.ChronoUnit;
import java.util.Optional;

/**
 * A class that represents a deadline task
 */
public class Deadline extends TaskAbstract {
    protected LocalDate date;
    protected Optional<LocalTime> timeOptional;

    /**
     * Constructs a new deadline task
     *
     * @param description description of the task
     * @param deadline deadline of the task
     */
    public Deadline(String description, String deadline) {
        super(description);
        String[] delimited = deadline.split(" ");
        String endDate = delimited[0];
        String year = endDate.substring(0, 4);
        String month = endDate.substring(5, 7);
        String day = endDate.substring(8, 10);
        this.date = LocalDate.parse(String.format("%s-%s-%s", year, month, day));
        if (delimited.length > 1) {
            this.timeOptional = Optional.<LocalTime>of(LocalTime.parse(delimited[1].substring(0, 2) + ":" + delimited[1]
                    .substring(2)));
        } else {
            this.timeOptional = Optional.<LocalTime>empty();
        }
    }

    /**
     * Return the date and time of the deadline written in format for printing to the terminal
     *
     * @return date and time string formatted for printing to terminal
     */
    private String getDateTimeForPrinting() {
        return this.date.format(DateTimeFormatter.ofPattern("dd MMM yyyy")) + (this.timeOptional.map(
                localTime -> " " + localTime.truncatedTo(ChronoUnit.MINUTES)).orElse(""));
    }

    /**
     * Return the date and time of the deadline written in format for saving to memory
     *
     * @return date and time string formatted for saving to memory
     */
    private String getDateTimeForSaving() {
        return this.date + (this.timeOptional.map(localTime -> " " + localTime.truncatedTo(
                ChronoUnit.MINUTES).toString().replace(":", "")).orElse(""));
    }

    /**
     * Return the deadline task into an appropriate string format for saving to memory
     *
     * @return string format of deadline task for saving to memory
     */
    public String saveToTextFormat() {
        return String.format("D | %s | %s | %s", this.isDone ? "1" : "0",
                this.description, this.getDateTimeForSaving());
    }

    /**
     * Print the current status of the deadline task to the terminal
     */
    @Override
    public void printStatus() {
        System.out.printf("[D][%s] %s (by: %s)\n", this.isDone ? "X" : " ",
                this.description, this.getDateTimeForPrinting());
    }
}
