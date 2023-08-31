package tasks;

import java.util.Optional;
import java.time.LocalDate;
import java.time.LocalTime;

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
            this.startTime = Optional.<LocalTime>of(LocalTime.parse(startDateArr[1]));
        } else {
            this.startTime = Optional.<LocalTime>empty();
        }

        if (endDateArr.length > 1) {
            this.endTime = Optional.<LocalTime>of(LocalTime.parse(endDateArr[1]));
        } else {
            this.endTime = Optional.<LocalTime>empty();
        }
    }

    private String getStartDateTime() {
        return this.startDate.toString() + (this.startTime.isEmpty() ? "" : " " + this.startTime.get().toString());
    }

    private String getEndDateTime() {
        return this.endDate.toString() + (this.endTime.isEmpty() ? "" : " " + this.endTime.get().toString());
    }

    public String saveToTextFormat() {
        return String.format("E | %s | %s | %s - %s", this.isDone ? "1" : "0", this.description, this.
                getStartDateTime(), this.getEndDateTime());
    }

    @Override
    public void printStatus() {
        System.out.printf("[E][%s] %s (from: %s to: %s)\n", this.isDone ? "X" : " ", this.description, this.
                getStartDateTime(), this.getEndDateTime());
    }
}
