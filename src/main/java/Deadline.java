public class Deadline extends Task {

    private String date;
    public Deadline(String task, String date) {
        super(task);
        this.date = date;
    }

    @Override
    public String status() {
        return isDone() ? "[D][X]" : "[D][ ]";
    }

    @Override
    public String taskName() {
        return super.taskName() + " (by: " + date + ")";
    }


    @Override
    public String type() {
        return "Deadline";
    }
}
