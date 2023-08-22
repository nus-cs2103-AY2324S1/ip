public class DeadlineTask extends Task {
    public DeadlineTask(String title) {
        super(title);
    }

    @Override
    public String toString() {
        return "[D] " + super.toString();
    }
}
