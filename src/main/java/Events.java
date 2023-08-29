public class Events extends Task {

    private String from;
    private String to;
    
    public Events(String task, String from, String to) throws DukeException {
        super(task);

        // Throws error if format is incorrect.
        if (!from.startsWith("from") || !to.startsWith("to")) {
            throw new DukeException("☹ OOPS!!! Please use the proper format for the event.");
        }

        // Throws error if no 'from' and 'to' details are given.
        if (from.substring(5).isEmpty() || to.substring(3).isEmpty()) {
            throw new DukeException("☹ OOPS!!! There are missing details for the event.");
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

    public String addDetailsToStorage() {
        return String.format("| from %s| to %s", this.from, this.to);
    }
    @Override
    public String addToStorage() {
        return String.format("E %s%s%n", super.addToStorage(), this.addDetailsToStorage());
    }
}
