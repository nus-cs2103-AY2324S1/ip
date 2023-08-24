import Exceptions.EmptyDescriptionException;

public class Event extends Task{
    private String from;
    private String to;

    Event(String s) throws EmptyDescriptionException {

        super(s.split("/from")[0]);
        this.from =  s.split("/from")[1].split("/to")[0].trim();
        this.to = s.split("/to")[1].trim();
    }

    public String toString(){
        return "[E]" + super.toString() + " (from: " + from +" to: "+ to +")";
    }
}
