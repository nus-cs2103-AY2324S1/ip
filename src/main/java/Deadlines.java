public class Deadlines extends Task{
    private String deadline;
    public Deadlines(String name, String deadline) {
        super(name);
        this.deadline = deadline;
    }

    @Override
    public String toString() {
        return String.format("[D] %s %s (by: %s)", super.getMarking(), super.name, this.deadline);
    }
}
