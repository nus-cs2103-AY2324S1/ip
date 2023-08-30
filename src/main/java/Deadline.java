public class Deadline extends Task {
    String deadline;

    //Constructor
    public Deadline(String name, String deadline) {
        super(name);
        this.isCompleted = false;
        this.deadline = deadline;
    }

    @Override
    public String toString() {
        return "[D]" + super.toString() + "(" + this.deadline + ")";
    }
}


