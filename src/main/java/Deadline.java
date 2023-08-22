public class Deadline extends Task {

    private String deadline;

    public Deadline(String description, String deadline) {
        super(description);
        this.deadline = deadline;
    }

    @Override
    public void displayTask(int index) {
        if (super.completed) {
            System.out.println(String.format("\t \t \t \t %d) [Deadline] [X] " + super.action + " (" + this.deadline + ")", index));
        } else {
            System.out.println(String.format("\t \t \t \t %d) [Deadline] [ ] " + super.action + " (" + this.deadline + ")", index));
        }
    }
}
