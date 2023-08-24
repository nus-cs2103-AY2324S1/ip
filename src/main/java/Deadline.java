public class Deadline extends Task{

    private String deadline;

    Deadline(String label, String deadline) {
        super(label);
        this.deadline = deadline;
    }

    @Override
    public String toString() {
        return "[D]" + super.toString() + " (by: " + deadline + ")";
    }
    
}
