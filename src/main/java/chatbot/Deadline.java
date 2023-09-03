package chatbot;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

/**
  * Stores the task details for deadlines.
  */
public class Deadline extends Task {
    private String input;
    private String time;

    /**
     * Constructor for Deadline. Initialises the description + extras.
     */
    public Deadline(String description, String input) {
        super(description);
        this.input = input;
    }

    @Override
    public String getTypeIcon() {
        return "[D]";
    }

    @Override
    public String getExtras() {
        String[] dateDetails = input.split(" ", 2);

        LocalDate localDate = LocalDate.parse(dateDetails[0]);
        time = " " + dateDetails[1];

        return "(by: " 
                    + localDate.format(DateTimeFormatter.ofPattern("MMM d yyyy")) 
                        + time + ")";
    }

    @Override
    public String toString() {
        return getTypeIcon() + super.getStatusIcon() +
                    super.description + getExtras();
    }
}