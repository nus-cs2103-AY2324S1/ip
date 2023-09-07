public class Deadline extends Task {
    private String deadline;

    public Deadline(String Task, String deadline) {
        super(Task);
        this.deadline = deadline;
    }

    @Override
    public String getStatus(){
        String time = "(by: " + deadline + ")";
        return "[Deadline]" + super.getStatus() + " " + time;
    }
}