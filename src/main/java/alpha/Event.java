package alpha;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.time.LocalTime;

/**
 * Class representation of an event which has both a start and end time.
 */
public class Event extends Task {

    private LocalDate startDate;
    private LocalDate endDate;
    private LocalTime startTime;
    private LocalTime endTime;

    private Event(String description, LocalDate startDate, LocalTime startTime, LocalDate endDate, LocalTime endTime) {
        super(description);
        this.startDate = startDate;
        this.startTime = startTime;
        this.endDate = endDate;
        this.endTime = endTime;
    }

    private Event(String description, LocalDate startDate, LocalDate endDate, LocalTime endTime) {
        super(description);
        this.startDate = startDate;
        this.endDate = endDate;
        this.endTime = endTime;
    }

    private Event(String description, LocalDate startDate, LocalTime startTime, LocalDate endDate) {
        super(description);
        this.startDate = startDate;
        this.startTime = startTime;
        this.endDate = endDate;
    }

    private Event(String description, LocalDate startDate, LocalDate endDate) {
        super(description);
        this.startDate = startDate;
        this.endDate = endDate;
    }

    public static Event makeEvent(String description, String start, String end) throws InvalidFormatException,
            DateTimeParseException {
        String trimmedDescription = description.trim();
        String trimmedStart = start.trim();
        String trimmedEnd = end.trim();
        String[] splitStart = trimmedStart.split(" ");
        String[] splitEnd = trimmedEnd.split(" ");
        if (trimmedDescription.length() == 0) {
            throw new InvalidFormatException("Missing a description!", TaskException.TaskType.EVENT);
        }
        if (splitStart.length == 2 && splitEnd.length == 2) {
            return new Event(trimmedDescription, LocalDate.parse(splitStart[0]), LocalTime.parse(splitStart[1],
                    DateTimeFormatter.ofPattern("HHmm")), LocalDate.parse(splitEnd[0]),
                    LocalTime.parse(splitEnd[1], DateTimeFormatter.ofPattern("HHmm")));
        } else if (splitStart.length == 2 && splitEnd.length == 1) {
            return new Event(trimmedDescription, LocalDate.parse(splitStart[0]), LocalTime.parse(splitStart[1],
                    DateTimeFormatter.ofPattern("HHmm")), LocalDate.parse(splitEnd[0]));
        } else if (splitStart.length == 1 && splitEnd.length == 2) {
            return new Event(trimmedDescription, LocalDate.parse(splitStart[0]), LocalDate.parse(splitEnd[0]),
                    LocalTime.parse(splitEnd[1], DateTimeFormatter.ofPattern("HHmm")));
        } else if (splitStart.length == 1 && splitEnd.length == 1) {
            return new Event(trimmedDescription, LocalDate.parse(splitStart[0]), LocalDate.parse(splitEnd[0]));
        } else {
            return null;
        }
    }

    public String getStart() {
        return startDate.toString() + getStartTime();
    }

    public String getEnd() {
        return endDate.toString() + getEndTime();
    }

    private String getStartTime() {
        if (startTime != null) {
            return " " + startTime.format(DateTimeFormatter.ofPattern("HHmm"));
        }
        return "";
    }

    private String getEndTime() {
        if (endTime != null) {
            return " " + endTime.format(DateTimeFormatter.ofPattern("HHmm"));
        }
        return "";
    }

    @Override
    public String toString() {
        String output = "[E]" + super.toString();
        String startDate = " (from: " + this.startDate.format(DateTimeFormatter.ofPattern("MMM d yyyy"));
        String startTime = (this.startTime != null) ? " " + this.startTime.format(DateTimeFormatter.ofPattern("HH:mm")) : "";
        String endDate = " to: " + this.endDate.format(DateTimeFormatter.ofPattern("MMM d yyyy"));
        String endTime = (this.endTime != null) ? " " + this.endTime.format(DateTimeFormatter.ofPattern("HH:mm")) + ")" : ")";
        return output + startDate + startTime + endDate + endTime;
    }
}
