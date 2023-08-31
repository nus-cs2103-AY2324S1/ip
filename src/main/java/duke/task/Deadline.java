package duke.task;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class Deadline extends Task{
    private LocalDateTime dueDate;


    /**
     * Creates a new Event object with text, dueDate, set type "T" with super constructor from Task
     *
     * @param text The text description of the task.
     * @param dueDate The dueDate of the task.
     */
    public Deadline (String text, LocalDateTime dueDate){
        super(text);
        super.setType("D");
        this.dueDate = dueDate;
    }

    /**
     * Creates a new Event object with the given text and checked status also set Type as "T"
     * Mainly used for registering input from local file
     *
     * @param text The text description of the task.
     * @param dueDate The dueDate of the task.
     * @param checked The status of whether the task is checked (completed) or not.
     */
    public Deadline (String text, LocalDateTime dueDate,boolean checked){
        super(text,checked);
        super.setType("D");
        this.dueDate = dueDate;
    }

    /**
     * Gets the formatted text including type, checked status, formatted startDate & endDate, and text description.
     *
     * @return The formatted text of the task.
     */
    @Override
    public String getTypeCheckedText(){
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("HH:mm MMM dd yyyy");
        String formattedDateTime = dueDate.format(formatter);


        String result = getType() + getChecked() + " " + getText() + " (by: " + formattedDateTime+")";
        return result;
    }

    /**
     * Gets the task's data in a parsed format to be imputed into our local file
     *
     * @return The parsed data of the task.
     */
    @Override
    public String getParsed(){
        String result = super.getParsed() + ";" + this.dueDate;
        return result;
    }




}
