public class Event extends Task {

    protected String from;
    protected String to;

    public Event(String description, String from, String to) {
        super(description);
        this.from = from;
        this.to = to;
    }

    public static Event createEventFromCommand(String command) {
        String[] tmpSplit = command.substring(6).split(" /from ");
        String taskDescription = tmpSplit[0];
        String[] split = tmpSplit[1].split(" /to ");
        String from = split[0];
        String to = split[1];
        return new Event(taskDescription, from, to);
    }
    
    @Override
    public String toString() {
        return "[E]" + super.toString() + " (from: " + from + " to: " + to +")";
    }
    
}
