package duke;

public class Event extends Task{
    protected String start;
    protected String end;

    public Event(String description, String start, String end){
        super(description);
        this.start = start;
        this.end = end;
        this.tag = Tag.E;
    }

    @Override
    public String toString(){
        return String.format("%s (from: %s to: %s)",description,start,end);
    }

}
