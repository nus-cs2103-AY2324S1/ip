public class Event extends Task{

    protected String start;
    protected String end;

    public Event(String list, String start, String end) {
        super(list);
        this.start = start;
        this.end = end;
    }
    @Override
    public String setMarked() {
        super.setMarked();
        return "Nice! I've marked this task as done:\n" + toString();
    }

    @Override
    public String setUnmarked() {
        super.setUnmarked();
        return "OK, I've marked this task as not done yet:\n" + toString();
    }

    //Check index is calling correct method in task
    @Override
    public String toString() {
        return "[E]"+ super.toString() + " ( from: " + this.start + " to: " + this.end +")";
    }



}
