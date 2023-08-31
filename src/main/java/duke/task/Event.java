package duke.task;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class Event extends Task{
    private LocalDateTime startDate;
    private LocalDateTime endDate;

    /**
     * Creates a new Event object with text,startDate,endDate, set type "T" with super constructor from Task
     *
     * @param text The text description of the task.
     * @param startDate The startDate of the task.
     * @param endDate The endDate of the task.
     */
    public Event(String text, LocalDateTime startDate, LocalDateTime endDate) {
        super(text);
        super.getText();
        super.setType("E");
        this.startDate = startDate;
        this.endDate= endDate;
    }

    /**
     * Creates a new Event object with the given text and checked status also set Type as "E"
     * Mainly used for registering input from local file
     *
     * @param text The text description of the task.
     * @param startDate The startDate of the task.
     * @param endDate The endDate of the task.
     * @param checked The status of whether the task is checked (completed) or not.
     */
    public Event(String text, LocalDateTime startDate, LocalDateTime endDate, boolean checked) {
        super(text,checked);
        super.getText();
        super.setType("E");
        this.startDate = startDate;
        this.endDate= endDate;

    }

    /**
     * Gets the formatted text including type, checked status, formatted startDate & endDate, and text description.
     *
     * @return The formatted text of the task.
     */
    @Override
    public String getTypeCheckedText() {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("MMM dd yyyy");
        String formattedStartDateTime = startDate.format(formatter);
        String formattedEndDateTime = endDate.format(formatter);

        String result = getType() + getChecked() + " " + getText() + " (from: " + formattedStartDateTime + " to: "+formattedEndDateTime+")";
        return result;
    }

    /**
     * Gets the task's data in a parsed format to be imputed into our local file
     *
     * @return The parsed data of the task.
     */
    @Override
    public String getParsed() {
        String result = super.getParsed() + ";" + this.startDate + ";" + this.endDate;
        return result;
    }





}
