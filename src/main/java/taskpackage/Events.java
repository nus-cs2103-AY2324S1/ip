package taskpackage;

import dukepackage.DukeException;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

public class Events extends Task {

    private LocalDate from;
    private LocalDate to;

    public Events(String task, String from, String to, String isDone) throws DukeException {
        super(task, isDone);
        
        try {
            this.from = LocalDate.parse(from.substring(5).replaceAll("\\s", ""));
            this.to = LocalDate.parse(to.substring(3));
        } catch (IndexOutOfBoundsException e) {
            throw new DukeException("☹ OOPS!!! There are missing details for the event.");
        } catch (DateTimeParseException e) {
            throw new DukeException("☹ OOPS!!! Please use the proper format for the deadline (YYYY-MM-DD).");
        }
    }
    
    public String printDetails() {
        return String.format("(from: %s to: %s)",
                this.from.format(DateTimeFormatter.ofPattern("dd MMMM yyyy")),
                this.to.format(DateTimeFormatter.ofPattern("dd MMMM yyyy")));
    }

    @Override
    public String printTask() {
        return String.format("[E]%s%s", super.printTask(), this.printDetails());
    }

    public String addDetailsToStorage() {
        return String.format("| from %s | to %s", this.from, this.to);
    }
    @Override
    public String addToStorage() {
        return String.format("E %s%s%n", super.addToStorage(), this.addDetailsToStorage());
    }
}
