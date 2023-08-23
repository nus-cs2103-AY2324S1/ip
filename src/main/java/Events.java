public class Events extends Task {
    private String from;
    private String to;
    public Events(String task, String from, String to) {
        super(task);
        this.from = from.substring(5);
        this.to = to.substring(3);
    }
    public String printDetails() {
        return String.format("(from: %sto: %s)", this.from, this.to);
    }

    @Override
    public String printTask() {
        return String.format("[E]%s%s", super.printTask(), this.printDetails());
    }
}
