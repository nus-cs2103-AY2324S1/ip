public class Deadline extends Task{
    private String deadline;
    public Deadline(String name, String deadline) {
        super(name);
        this.type = "D";
        this.deadline = deadline;
    }
    @Override
    public String toString() {
        return super.toString() + "(by:" + deadline +")";
    }
}
