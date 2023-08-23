public class Deadlines extends Task {
    private final String deadline;
    public Deadlines(String task, String deadline) {
        super(task);
        this.deadline = deadline;
    }

    @Override
    public void mark() {
        this.done = true;
        System.out.println(super.line() + "Okay, I have marked this task as completed!" + "\n" + this.toString());
        System.out.println(super.line());
    }
    @Override
    public void unMark() {
        this.done = false;
        System.out.println(super.line() + "Okay, I have marked this task as incomplete!" + "\n" + this.toString());
        System.out.println(super.line());
    }
    @Override
    public String toString() {
        String checkbox = this.done ? "[X] " : "[ ] ";
        String submitDate = "(by: " + deadline.substring(3) + ")";
        return "[D]" + checkbox + task + " " + submitDate;
    }
}
