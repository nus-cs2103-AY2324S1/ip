public class Deadline extends Task {
    private String deadline;

    public Deadline(String title, String deadline) {
        super(title, false);
        this.deadline = deadline;
    }
    @Override
    public String toString() {
        if (this.done == true) {
            return "[D] " + "[X] " + this.title;
        }
        return "[D] " + "[ ] " + this.title;
    }
}


