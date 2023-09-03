package tasks;

import java.time.DateTimeException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

public class Event extends Task {
    String[] split1;
    String[] splitStart;
    String[] splitEnd;
    String startDate;

    public Event(String content, boolean status) {
        super(content, status);
        split1 = content.split("/", 2);
        splitStart = split1[1].split(" ", 3);
        String[] split2 = splitStart[2].split("/", 2);
        splitEnd = split2[1].split(" ", 2);
        try {
            this.startDate = LocalDate.parse(splitStart[1].replace("/", "-")).
                    format(DateTimeFormatter.ofPattern("MMM d yyyy")) + " " + split2[0];
        } catch (DateTimeParseException e) {
            try {
                DateTimeFormatter formatter = DateTimeFormatter.ofPattern("d/MM/yyyy");
                this.startDate = LocalDate.parse(splitStart[1], formatter).
                        format(DateTimeFormatter.ofPattern("MMM d yyyy")) + " " + split2[0];

            } catch (DateTimeException ex) {
                this.startDate = splitStart[1] + " " + split2[0];
            }
        }


    }

    public Event mark() {
        return new Event(super.getContent(), true);
    }

    public Event unmark() {
        return new Event(super.getContent(), false);
    }

    public String addTask(int listSize) {
        return "____________________________________________________________\n" +
                "Got it. I've added this task:\n" +
                toString() + "\n" +
                String.format("Now you have %d tasks in the list,\n", listSize) +
                "____________________________________________________________";
    }

    public String toString() {
        String[] result = split1[0].split(" ", 2);
        if (!super.isMarked()) {
            return String.format("[E][ ] %s(%s: %s%s: %s)", result[1], splitStart[0], startDate, splitEnd[0], splitEnd[1]);
        } else {
            return String.format("[E][X] %s(%s: %s%s: %s)", result[1], splitStart[0], startDate, splitEnd[0], splitEnd[1]);
        }
    }
    // main branch commit for Event
}
