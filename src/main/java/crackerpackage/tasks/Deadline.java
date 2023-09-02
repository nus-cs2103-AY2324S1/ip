package crackerpackage.tasks;

import Exceptions.EmptyDescriptionException;
import Exceptions.IllegalFormatException;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

/**
 * A task that has a date as a deadline.
 *
 * @author Anton Tan Hong Zhi
 */
public class Deadline extends Task{
    private LocalDate deadline;

    /**
     * Creates a Deadline object with input from the user.
     *
     * @param s A string containing the description and date of a Deadline object
     * @throws EmptyDescriptionException
     * @throws IllegalFormatException
     */
    public Deadline(String s) throws EmptyDescriptionException, IllegalFormatException {
        super(s.split("/by",0)[0].trim());
        if(!s.contains("/by")){
            throw new IllegalFormatException("Missing \"/by\" after task description");
        }
        try{
            this.deadline = LocalDate.parse(s.split("/by",0)[1].trim());
        } catch (Exception e){
            throw new IllegalFormatException("please enter date in yyyy-mm-dd format");
        }

    }

    /**
     * Creates a Deadline object with input from a file.
     *
     * @param desc The description of a Deadline Task
     * @param deadline The date to complete before the Deadline
     * @throws EmptyDescriptionException
     */
    public Deadline(String desc, String deadline) throws EmptyDescriptionException {
        super(desc);
        this.deadline = LocalDate.parse(deadline);
    }

    /**
     * Returns the string representation of the Deadline.
     *
     * @return a string representing the Deadline.
     */
    public String toString(){
        return "[D]" + super.toString() + " (by: " + getDeadline() +")";
    }

    /**
     * Returns the string representation of the date of the Deadline.
     *
     * @return a string representing the date of the Deadline.
     */
    public String getDeadline(){ return deadline.format(DateTimeFormatter.ofPattern("MMM dd yyyy"));}
}