package duck.task;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

import duck.DuckException;

public class EventTask extends Task {
    LocalDate start;
    LocalDate end;

    public EventTask(String name, boolean isDone, LocalDate start, LocalDate end) {
        super(name,  isDone);
        this.start = start;
        this.end = end;
    }

    public String formatStart() {
        return start.format(DateTimeFormatter.ofPattern("MMM dd yyyy"));
    }

    public String formatEnd() {
        return end.format(DateTimeFormatter.ofPattern("MMM dd yyyy"));
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

    public static EventTask parse(String fileLine, DateTimeFormatter dateFormatter) throws DuckException {

        // Finding isDone
        boolean isDone = fileLine.charAt(1) == '1';

        // Finding name
        int slashIndex = fileLine.indexOf("/");
        int nameLength = Integer.parseInt(fileLine.substring(2, slashIndex));
        String name = fileLine.substring(slashIndex + 1, slashIndex + 1 + nameLength);

        // Finding start
        int secondSlashIndex = fileLine.indexOf("/", slashIndex + 1); // The index of the next slash
        String startString = fileLine.substring(secondSlashIndex + 1, secondSlashIndex + 12);
        LocalDate start = LocalDate.parse(startString, dateFormatter);

        // Finding end
        int thirdSlashIndex = fileLine.indexOf("/", secondSlashIndex + 1); // The index of the next slash
        String endString = fileLine.substring(thirdSlashIndex + 1);
        LocalDate end = LocalDate.parse(endString, dateFormatter);

        return new EventTask(name, isDone, start, end);
    }
}