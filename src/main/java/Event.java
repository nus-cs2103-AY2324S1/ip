public class Event extends Task {
    protected String start;
    protected String end;

    public Event(String input) {
        super(input.split("/")[0]);
        try {
            this.start = input.split("/")[1].replace("from ", "");
            this.end = input.split("/")[2].replace("to ", "");
        } catch (NullPointerException e) {
            throw new KieraException("write when your event starts and ends in the form: /from (start) /to (end))");
        }
    }

    public String getStart() {
        return this.start;
    }

    public String getEnd() {
        return this.end;
    }
    public String toStorageString() {
        return "E // " + this.getStatusIcon() + " // " + this.description + " /from " + this.start + " /to " + this.end;
    }
    public String toString() {
         return "[E]" 
         + "[" 
         + this.getStatusIcon()
         + "] " 
         + this.description 
         + " (from " 
         + this.start 
         + " - "
         + this.end
         + ")";
    }
}
