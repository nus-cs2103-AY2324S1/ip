import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.time.LocalTime;
import java.util.Arrays;

// Class representation of an event which has both a start and end time
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

    public static Event makeEvent(String description, String start, String end) {
        String trimmedDescription = description.trim();
        String trimmedStart = start.trim();
        String trimmedEnd = end.trim();
        String[] splitStart = trimmedStart.split(" ");
        String[] splitEnd = trimmedEnd.split(" ");
        try {
<<<<<<< HEAD
=======
<<<<<<< HEAD
=======
            if (trimmedDescription.length() == 0) {
                throw new InvalidFormatException("Missing a description!", TaskException.TaskType.EVENT);
            }
>>>>>>> branch-A-MoreOOP
>>>>>>> cb4c09e7b49931b4e4fa7e59677f9431e990efb0
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
<<<<<<< HEAD
=======
<<<<<<< HEAD
>>>>>>> cb4c09e7b49931b4e4fa7e59677f9431e990efb0
        } catch (DateTimeParseException e) {
            System.out.println("The date/time is in an invalid format! Enter" +
                    " the date in the format YYYY-MM-DD HHmm");
            return null;
<<<<<<< HEAD
=======
=======
        } catch (DateTimeParseException e1) {
            System.out.println("The date/time is in an invalid format! Enter" +
                    " the date in the format YYYY-MM-DD HHmm");
            return null;
        } catch (InvalidFormatException e2) {
            System.out.println(e2.getMessage() + " Please enter a description between " +
                    "the start and end timings of the event.");
            return null;
>>>>>>> branch-A-MoreOOP
>>>>>>> cb4c09e7b49931b4e4fa7e59677f9431e990efb0
        }
    }

    public String getStart() {
        return this.startDate.toString() + this.getStartTime();
    }

    public String getEnd() {
        return this.endDate.toString() + this.getEndTime();
    }

    private String getStartTime() {
        if (this.startTime != null) {
            return " " + this.startTime.format(DateTimeFormatter.ofPattern("HHmm"));
        }
        return "";
    }

    private String getEndTime() {
        if (this.endTime != null) {
            return " " + this.endTime.format(DateTimeFormatter.ofPattern("HHmm"));
        }
        return "";
    }

<<<<<<< HEAD
=======
<<<<<<< HEAD
>>>>>>> cb4c09e7b49931b4e4fa7e59677f9431e990efb0

    @Override
    public String toString() {
        String output = "[E]" + super.toString();

<<<<<<< HEAD
=======
=======
    @Override
    public String toString() {
        String output = "[E]" + super.toString();
>>>>>>> branch-A-MoreOOP
>>>>>>> cb4c09e7b49931b4e4fa7e59677f9431e990efb0
        String startDate = " (from: " + this.startDate.format(DateTimeFormatter.ofPattern("MMM d yyyy"));
        String startTime = (this.startTime != null) ? " " + this.startTime.format(DateTimeFormatter.ofPattern("HH:mm")) : "";
        String endDate = " to: " + this.endDate.format(DateTimeFormatter.ofPattern("MMM d yyyy"));
        String endTime = (this.endTime != null) ? " " + this.endTime.format(DateTimeFormatter.ofPattern("HH:mm")) + ")" : ")";
        return output + startDate + startTime + endDate + endTime;
    }
}
