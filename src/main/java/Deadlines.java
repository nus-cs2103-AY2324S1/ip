public class Deadlines extends Task {

    private String by;

    public Deadlines(String task, String details) throws DukeException {
        super(task);

        // Throws error if format is incorrect.
        if (!details.startsWith("by")) {
            throw new DukeException("☹ OOPS!!! Please use the proper format for the deadline.");
        }

        // Throws error if no 'by' details are given.
        if (details.substring(3).isEmpty()) {
            throw new DukeException("☹ OOPS!!! There are missing details for the deadline.");
        }
        
        this.by = details.substring(3);

    }

    public String printDetails() {
        return String.format("(by: %s)", this.by);
    }
    
    @Override
    public String printTask() {
        return String.format("[D]%s%s", super.printTask(), this.printDetails());
    }

    public String addDetailsToStorage() {
        return String.format("| by %s", this.by);
    }
    @Override
    public String addToStorage() {
        return String.format("D %s%s%n", super.addToStorage(), this.addDetailsToStorage());
    }
}
