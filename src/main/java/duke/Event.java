package duke;

public class Event extends Task{
    protected Tag tag = Tag.E;
    protected String start;
    protected String end;

    public Event(String description, String start, String end){
        super(description);
        this.start = start;
        this.end = end;

    }

}
