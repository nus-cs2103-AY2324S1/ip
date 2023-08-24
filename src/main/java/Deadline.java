public class Deadline extends Task {
    private String deadline;

    public Deadline(String task, String deadline) {
        super(task);
        this.deadline = deadline;
    }

    @Override
    public String getStatus(){
        String time = "(by: " + deadline + ")";
        return "[D]" + super.getStatus() + " " + time;
    }
}
