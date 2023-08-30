import Exceptions.EmptyDescriptionException;
import Exceptions.IllegalFormatException;

public class Event extends Task{
    private String from;
    private String to;

    Event(String s) throws EmptyDescriptionException, IllegalFormatException {

        super(s.split("/from")[0]);
        //Checking if format is correct
        if(!s.contains("/from")){
            throw new IllegalFormatException("Missing \"/from\" after Event Description");
        } else if(!s.contains("/to")){
            throw new IllegalFormatException("Missing \"/to\" after Event Description");
        } else if(s.indexOf("/from") > s.indexOf("/to")){
            throw new IllegalFormatException("Format of Adding Event is: \nevent eventDescription /from start /to end ");
        } else if(s.split("/to").length <= 1){
            throw new IllegalFormatException("end time is empty");
        }

        //initialize variables
        this.from =  s.split("/from")[1].split("/to")[0].trim();
        this.to = s.split("/to")[1].trim();

        //check if they are empty strings
        if(this.from.equals("")){
            throw new IllegalFormatException("Missing start time");
        }
    }

    public Event(String desc, String from, String to) throws EmptyDescriptionException{
        super(desc);
        this.from = from;
        this.to = to;

    }
    public String getStart(){ return from;}
    public String getEnd(){return to;}
    public String toString(){
        return "[E]" + super.toString() + " (from: " + from +" to: "+ to +")";
    }
}
