public class Deadlines extends Task {
    private String deadline;

    public static Deadlines create(String rawLine) {
        String[] instructions = rawLine.split(" /by ");
        return new Deadlines(instructions[0], instructions[1]);
    }
    public Deadlines(String item, String deadline) {
        super(item);
        this.deadline = deadline;
    }

    @Override
    public String toString() {
        return String.format(
                "[D][%s] %s (by %s)",
                super.getStatusIcon(),
                super.description,
                this.deadline
        );
    }
}