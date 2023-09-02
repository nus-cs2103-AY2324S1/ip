package duck.task;

import java.time.LocalDate;

import duck.DuckException;
import static duck.Parser.OUTPUT_DATE_FORMAT;

public class EventTask extends Task {
    LocalDate start;
    LocalDate end;

    public EventTask(String name, boolean isDone, LocalDate start, LocalDate end) {
        super(name,  isDone);
        this.start = start;
        this.end = end;
    }

    public String formatStart() {
        return start.format(OUTPUT_DATE_FORMAT);
    }

    public String formatEnd() {
        return end.format(OUTPUT_DATE_FORMAT);
    }

    @Override
    public String stringify() {
        String startString = formatStart();
        String endString = formatEnd();
        return "E" + super.stringify() + 
                startString.length() + "/" + startString + 
                endString.length() + "/" + endString;
    }

    @Override
    public String toString() {
        return "[E]" + super.toString() + " (from: " + formatStart() + " to " + formatEnd() + ")";
    }

    @Override
    public boolean equals(Object obj) {
        if (obj instanceof EventTask) {
            EventTask other = (EventTask) obj;
            return super.equals(other) && start.equals(other.start) && end.equals(other.end);
        }
        return false;
    }

    public static EventTask parse(String fileLine) throws DuckException {

        // Finding isDone
        boolean isDone = fileLine.charAt(1) == '1';

        // Finding name
        int slashIndex = fileLine.indexOf("/");
        int nameLength = Integer.parseInt(fileLine.substring(2, slashIndex));
        String name = fileLine.substring(slashIndex + 1, slashIndex + 1 + nameLength);

        // Finding start
        int secondSlashIndex = fileLine.indexOf("/", slashIndex + 1); // The index of the next slash
        String startString = fileLine.substring(secondSlashIndex + 1, secondSlashIndex + 12);
        LocalDate start = LocalDate.parse(startString, OUTPUT_DATE_FORMAT);

        // Finding end
        int thirdSlashIndex = fileLine.indexOf("/", secondSlashIndex + 1); // The index of the next slash
        String endString = fileLine.substring(thirdSlashIndex + 1);
        LocalDate end = LocalDate.parse(endString, OUTPUT_DATE_FORMAT);

        return new EventTask(name, isDone, start, end);
    }
}