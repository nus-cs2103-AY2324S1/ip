import java.time.DateTimeException;
import java.time.format.DateTimeFormatter;
import java.time.LocalDate;
import java.util.Arrays;
import java.util.List;

public class Event extends Task {
    private LocalDate from;
    private LocalDate to;
    private static String emptyDate = "Please specify event start and end dates using /from and /to\n" + 
        "e.g. event holiday /from 2023-06-01 /to 2023-06-30";
    private static String invalidDate = "Please provide dates with the following format: YYYY-MM-DD";
    private static String invalidEndDate = "Your end date is before start date";

    Event(String name, LocalDate from, LocalDate to) {
        super(name);
        this.from = from;
        this.to = to;
    }

    Event(String name, boolean isDone, LocalDate from, LocalDate to) {
        super(name, isDone);
        this.from = from;
        this.to = to;
    }

    public static Event create(String[] queries) throws DukeException {
        List<String> queryList = Arrays.asList(queries);
        if (queryList.size() < 2) {
            throw new DukeException("The description of a todo cannot be empty.");
        }
        String name = "";
        String fromStr = "", toStr = "";
        int fromIndex = (int) 1e9, toIndex = (int) 1e9;
        for (int i = 1; i < queryList.size(); i++) {
            if (queryList.get(i).equals("/from")) {
                fromIndex = i;
                continue;
            }
            if (queryList.get(i).equals("/to")) {
                toIndex = i;
                continue;
            }
            if (i < fromIndex) {
                name += i > 1 ? " " : "";
                name += queryList.get(i);
            } else if (i < toIndex) {
                fromStr += i > fromIndex + 1 ? " " : "";
                fromStr += queryList.get(i);
            } else {
                toStr += i > toIndex + 1 ? " " : "";
                toStr += queryList.get(i);
            }
        }
        if (fromStr.equals("") || toStr.equals("")) {
            throw new DukeException(emptyDate);
        }
        try {
            LocalDate from = LocalDate.parse(fromStr);
            LocalDate to = LocalDate.parse(toStr);
            if (from.compareTo(to) > 0) {
                throw new DukeException(invalidEndDate);
            }
            return new Event(name, from, to);
        } catch (DateTimeException e) {
            throw new DukeException(invalidDate);
        }
    }

    @Override
    public String toString() {
        return String.format("[E]%s (%s - %s)", super.toString(), 
            this.from.format(DateTimeFormatter.ofPattern("d MMM yyyy")), 
            this.to.format(DateTimeFormatter.ofPattern("d MMM yyyy")));
    }

    @Override
    public String exportToText() {
        return String.format("event,>%s,>%s,>%s", super.exportToText(), this.from, this.to);
    }
}
