public class Event extends Task{
    private String from;
    private String to;
    public Event (String name, String from, String to) {
        super(name);
        this.from = from;
        this.to = to;
    }

    @Override
    public String showTaskinList() {
        return "[E]" + super.showTaskinList() + "(" + "from: " + this.from + " to: " + this.to + ")";
    }
}
