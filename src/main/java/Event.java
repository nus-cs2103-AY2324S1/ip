public class Event extends Task {
    protected String starting;
    protected String ending;

    public Event(String description){
        super(description.substring(0, description.indexOf("/from")));
        int fromIndex = description.indexOf("/from");
        String tempStarting = description.substring(fromIndex + 5);
        int toIndex = tempStarting.indexOf("/to") + fromIndex + 6;
        this.starting = tempStarting.substring(0, toIndex - fromIndex - 6);
        this.ending = description.substring(toIndex + 2);
    }
    @Override
    public String toString() {
        return "[E] [" + this.getStatusIcon() + "] " + this.description + "(from:" + starting + "to:" + ending + ")";
    }
}
