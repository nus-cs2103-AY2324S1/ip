package duke.task;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class Event extends Task{
    private LocalDateTime startDate;
    private LocalDateTime endDate;

    public Event(String text, LocalDateTime startDate, LocalDateTime endDate) {
        super(text);
        super.getText();
        super.setType("E");
        this.startDate = startDate;
        this.endDate= endDate;
    }
    @Override
    public String getTypeCheckedText() {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("MMM dd yyyy");
        String formattedStartDateTime = startDate.format(formatter);
        String formattedEndDateTime = endDate.format(formatter);

        String result = getType() + getChecked() + " " + getText() + " (from: " + formattedStartDateTime + " to: "+formattedEndDateTime+")";
        return result;
    }

    @Override
    public String getParsed() {
        String result = super.getParsed() + ";" + this.startDate + ";" + this.endDate;
        return result;
    }





}
