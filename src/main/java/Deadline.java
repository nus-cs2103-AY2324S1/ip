public class Deadline extends Task {
    private String date;

    public Deadline(int status, String task, String date) {
        super(status, task);
        this.date = date;
    }
    @Override
    public String convertTask() {
        return "D | " +  super.getStatus() + " | " + super.getTask() +
                " | " + date;
    }

    @Override
    public String toString() {
        return "[D] " + super.toString() + " (by: " + date + ")";
    }
}
