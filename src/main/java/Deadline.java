public class Deadline extends Task {
    private String deadline;
    public Deadline(String description, String deadline) {
        super(description);
        this.deadline = deadline;
    }
    @Override
    public  String saveString() {
        return "D" + super.saveString() + " | " + this.deadline;
    }
<<<<<<< HEAD
=======
    @Override
    public  String saveString() {
        return "D" + super.saveString() + " | " + this.deadline;
    }
>>>>>>> branch-Level-7
    @Override
    public String toString() {
        return String.format("[D] %s (by: %s)", super.toString(), this.deadline);
    }
}