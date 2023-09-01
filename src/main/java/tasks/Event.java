package tasks;

import Exceptions.EmptyDescriptionException;
import Exceptions.IllegalFormatException;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class Event extends Task{
    private LocalDate from;
    private LocalDate to;

    public Event(String s) throws EmptyDescriptionException, IllegalFormatException {

        super(s.split("/from")[0]);
        //Checking if format is correct
        if(!s.contains("/from")){
            throw new IllegalFormatException("Missing \"/from\" after tasks.Event Description");
        } else if(!s.contains("/to")){
            throw new IllegalFormatException("Missing \"/to\" after tasks.Event Description");
        } else if(s.indexOf("/from") > s.indexOf("/to")){
            throw new IllegalFormatException("Format of Adding tasks.Event is: \nevent eventDescription /from start /to end ");
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

    public Event(String desc, String from, String to) throws EmptyDescriptionException{
        super(desc);
        this.from = LocalDate.parse(from);
        this.to = LocalDate.parse(to);

    }

    public String getStart(){ return from.format(DateTimeFormatter.ofPattern("MMM dd yyyy"));}
    public String getEnd(){return to.format(DateTimeFormatter.ofPattern("MMM dd yyyy"));}
    public String toString(){
        return "[E]" + super.toString() + " (from: " + getStart() +" to: "+ getEnd() +")";
    }
}