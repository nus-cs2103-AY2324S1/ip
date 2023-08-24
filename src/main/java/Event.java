public class Event extends Task {

    protected String from;
    protected String to;

    public Event(String description, int number, String from, String to) {
        super(description,number);
        this.from = from;
        this.to = to;
    }

    @Override
    public void displayTask() {
        System.out.println(number + ".[E]" + super.getStatusIcon() + description +
                "(from: " + from + "to: " + to + ")");
    }

    @Override
    public void addedTask() {
        System.out.println("Got it. I've added this task:");
        System.out.println("  [E]" + super.getStatusIcon() + description +
                "(from: " + from + "to: " + to + ")");
        if (number == 1) {
            System.out.println("Now you have 1 task in the list.");
        } else {
            System.out.println("Now you have " + number + " tasks in the list.");
        }
    }

    @Override
    public void displayTaskMark() {
        System.out.println("[E]" + super.getStatusIcon() + description +
                " (from: " + from + "to: " + to + ")");
    }
}
