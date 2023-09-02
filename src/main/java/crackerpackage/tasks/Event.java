package crackerpackage.tasks;

import Exceptions.EmptyDescriptionException;
import Exceptions.IllegalFormatException;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

/**
 * A task that has a starting date and an ending date.
 *
 * @author Anton Tan Hong Zhi
 */
public class Event extends Task{
    private LocalDate from;
    private LocalDate to;

    /**
     * Creates a Event object with input from the user.
     *
     * @param s A string containing the description and starting/ending dates of a Event object
     * @throws EmptyDescriptionException
     * @throws IllegalFormatException
     */
    public Event(String s) throws EmptyDescriptionException, IllegalFormatException {

        super(s.split("/from")[0]);
        //Checking if format is correct
        if(!s.contains("/from")){
            throw new IllegalFormatException("Missing \"/from\" after CrackerPackage.tasks.Event Description");
        } else if(!s.contains("/to")){
            throw new IllegalFormatException("Missing \"/to\" after CrackerPackage.tasks.Event Description");
        } else if(s.indexOf("/from") > s.indexOf("/to")){
            throw new IllegalFormatException("Format of Adding CrackerPackage.tasks.Event is: \nevent eventDescription /from start /to end ");
        } else if(s.split("/to").length <= 1){
            throw new IllegalFormatException("end time is empty");
        }

        //initialize variables
        try{
            this.from =  LocalDate.parse(s.split("/from")[1].split("/to")[0].trim());
            this.to = LocalDate.parse(s.split("/to")[1].trim());
            if(from.isAfter(to)){
                throw new IllegalFormatException("end time is earlier than start time");
            }
        } catch (IllegalFormatException e){
            throw e;
        } catch (Exception e){
            throw new IllegalFormatException("please enter date in yyyy-mm-dd format");
        }


        //check if they are empty strings
        if(this.from.equals("")){
            throw new IllegalFormatException("Missing start time");
        }
    }

    /**
     * Constructs a Deadline object with input from a file.
     *
     * @param desc Description of the Event object
     * @param from Starting date of the Event object
     * @param to Ending date of the Event object
     * @throws EmptyDescriptionException
     */
    public Event(String desc, String from, String to) throws EmptyDescriptionException{
        super(desc);
        this.from = LocalDate.parse(from);
        this.to = LocalDate.parse(to);

    }

    /**
     * Returns the starting date of the object.
     *
     * @return a string representing the starting date of the object
     */
    public String getStart(){ return from.format(DateTimeFormatter.ofPattern("MMM dd yyyy"));}

    /**
     * Returns the ending date of the object.
     *
     * @return a string representing the ending date of the object
     */
    public String getEnd(){return to.format(DateTimeFormatter.ofPattern("MMM dd yyyy"));}

    /**
     * Returns the string representation of the Event object.
     *
     * @return a string representing the Event object
     */
    public String toString(){
        return "[E]" + super.toString() + " (from: " + getStart() +" to: "+ getEnd() +")";
    }
}