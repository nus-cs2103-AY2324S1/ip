public class Event extends Task {
    private String from;
    private String to;
    private String num;
    public Event(String name, String from, String to) {
        super(name);
        this.from = from;
        this.to = to;
    }

    @Override
    public String getTaskDesc() {
        String[] newFrom = this.from.split("pm ");
        if (!this.getDone()) {
            return "E |" + " 0 | " + this.getName() + "| " + newFrom[0] + "-" + this.to;
        } else {
            return "E |" + "1 |" + this.getName() + "| " + newFrom[0] + "-" + this.to;
        }
    }
    @Override
    public String toString() {
        if (!this.getDone()) {
            System.out.println("TaskDesc: " + this.getTaskDesc());
            return  "[E][ ] " + this.getName() + "(from: " + this.from + "to: " + this.to + ")";
        } else {
            return "[E][X] " + this.getName() + "(from: " + this.from + "to: " + this.to + ")";
        }
    }
}
