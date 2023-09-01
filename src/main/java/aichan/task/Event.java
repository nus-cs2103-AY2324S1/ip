package aichan.task;

import aichan.AiChanException;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

public class Event extends Task {
    private LocalDateTime start;
    private LocalDateTime end;

    public Event(String[] strs) throws AiChanException {
        // inside this array has 3 elements
        // first is taskName, second element is start, third element is end time
        super(strs[0]);
        // assume that strs[1] follow the format 25/12/2019 1800
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("d/M/yyyy HHmm");
        try {
            this.start = LocalDateTime.parse(strs[1], formatter);
            this.end = LocalDateTime.parse(strs[2], formatter);
        } catch (DateTimeParseException e) {
            throw new AiChanException("Please enter the date and time with this format: 25/12/2019 1800");
        }
    }

    @Override
    public String toString() {
        DateTimeFormatter formatter2 = DateTimeFormatter.ofPattern("MMM d yyyy HHmm"); // Dec 25 2019 1800
        return String.format("[E]%s (from: %s to: %s)",
                             super.toString(), this.start.format(formatter2), this.end.format(formatter2));
    }

    @Override
    public String toFileLine() {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("d/M/yyyy HHmm");
        return String.format("E | %s | %s - %s",
                             super.toFileLine(), this.start.format(formatter), this.end.format(formatter));
    }
}
