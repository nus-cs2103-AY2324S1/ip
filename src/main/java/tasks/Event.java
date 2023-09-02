package tasks;

import java.time.temporal.ChronoUnit;
import java.util.Optional;
import java.time.LocalDate;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;

public class Event extends TaskAbstract {
    protected LocalDate startDate;
    protected LocalDate endDate;
    protected Optional<LocalTime> startTime;
    protected Optional<LocalTime> endTime;

    public Event(String description, String start, String end) {
        super(description);
        String[] startDateArr = start.split(" ");
        String[] endDateArr = end.split(" ");
        String eventStartDate = startDateArr[0];
        String eventEndDate = endDateArr[0];
        String startYear = eventStartDate.substring(0, 4);
        String endYear = eventEndDate.substring(0, 4);
        String startMonth = eventStartDate.substring(5, 7);
        String endMonth = eventEndDate.substring(5, 7);
        String startDay = eventStartDate.substring(8, 10);
        String endDay = eventEndDate.substring(8, 10);
        this.startDate = LocalDate.parse(String.format("%s-%s-%s", startYear, startMonth, startDay));
        this.endDate = LocalDate.parse(String.format("%s-%s-%s", endYear, endMonth, endDay));

        if (startDateArr.length > 1) {
            this.startTime = Optional.<LocalTime>of(LocalTime.parse(startDateArr[1].substring(0, 2) + ":"
                    + startDateArr[1].substring(2)));
        } else {
            this.startTime = Optional.<LocalTime>empty();
        }

        if (endDateArr.length > 1) {
            this.endTime = Optional.<LocalTime>of(LocalTime.parse(endDateArr[1].substring(0, 2) + ":"
                    + endDateArr[1].substring(2) + ":00"));
        } else {
            this.endTime = Optional.<LocalTime>empty();
        }
    }

    private String getStartDateTimeForPrinting() {
        return this.startDate.format(DateTimeFormatter.ofPattern("dd MMM yyyy")) + (this.startTime.map(localTime ->
                " " + localTime.truncatedTo(ChronoUnit.MINUTES)).orElse(""));
    }

    private String getEndDateTimeForPrinting() {
        return this.endDate.format(DateTimeFormatter.ofPattern("dd MMM yyyy")) + (this.endTime.map(localTime ->
                " " + localTime.truncatedTo(ChronoUnit.MINUTES)).orElse(""));
    }

    private String getStartDateTimeForSaving() {
        return this.startDate + (this.startTime.map(localTime -> " " + localTime.truncatedTo(
                ChronoUnit.MINUTES).toString().replace(":", "")).orElse(""));
    }


    private String getEndDateTimeForSaving() {
        return this.endDate + (this.endTime.map(localTime -> " " + localTime.truncatedTo(
                ChronoUnit.MINUTES).toString().replace(":", "")).orElse(""));
    }

    public String saveToTextFormat() {
        return String.format("E | %s | %s | %s - %s", this.isDone ? "1" : "0", this.description, this.
                getStartDateTimeForSaving(), this.getEndDateTimeForSaving());
    }

    @Override
    public void printStatus() {
        System.out.printf("[E][%s] %s (from: %s to: %s)\n", this.isDone ? "X" : " ", this.description, this.
                getStartDateTimeForPrinting(), this.getEndDateTimeForPrinting());
    }
}
