public class Event extends Task{
    protected String start ;
    protected String end ;

    public Event(String name, String start, String end){
        super(name);
        this.start = start;
        this.end = end;
    }

    //toString method
    @Override public String toString(){
        return "[E] " + super.toString() + " (from: " + start + " to: " + end + ")";
    }

}
