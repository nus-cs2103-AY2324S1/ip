public class Events extends Task {
    private String from;
    private String to;
    public Events(String task, String from, String to) throws DukeException {
        super(task);
        if (!from.startsWith("from") || !to.startsWith("to")) {
            throw new DukeException("☹ OOPS!!! Please use the proper format for the event.");
        }
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
