public class Event extends Task {
    private String from;
    private String to;

    public Event(String description, String from, String to) {
        super(description);
        this.from = from;
        this.to = to;
    }

    @Override
    public void displayTask(int index) {
        if (super.completed) {
            System.out.println(String.format("\t \t \t \t %d) [Event] ✅ " + super.action + " (" + this.from + " " + this.to + ")", index));
        } else {
            System.out.println(String.format("\t \t \t \t %d) [Event] ⬜ " + super.action + " (" + this.from + " " + this.to + ")", index));
        }
    }
}
