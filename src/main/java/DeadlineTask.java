public class DeadlineTask extends Task{
    public static String type = "D";
    private String deadline;
    public DeadlineTask(String name, String deadline) {
        super(name);
        this.deadline = deadline;
    }

    @Override
    public String toString() {
        return String.format("[%s][%s] %s (by: %s)",
                this.type,
                this.checkIsDone() ? "X" : " ",
                this.getName(),
                this.deadline);
    }
}
