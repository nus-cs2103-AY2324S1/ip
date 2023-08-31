package duke.storage;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class Deadline extends Task {
    private LocalDateTime dueDate;

    public Deadline (String text, LocalDateTime dueDate) {
        super(text);
        super.setType("D");
        this.dueDate = dueDate;
    }
    public Deadline (String text, LocalDateTime dueDate,boolean checked) {
        super(text,checked);
        super.setType("D");
        this.dueDate = dueDate;
    }
    @Override
    public String getTypeCheckedText() {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("HH:mm MMM dd yyyy");
        String formattedDateTime = dueDate.format(formatter);


        String result = getType() + getChecked() + " " + getText() + " (by: " + formattedDateTime+")";
        return result;
    }
    @Override
    public String getParsed() {
        String result = super.getParsed() + ";" + this.dueDate;
        return result;
    }




}
