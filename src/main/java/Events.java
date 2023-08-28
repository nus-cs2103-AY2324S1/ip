public class Events extends Task {

    private String startDate;

    private String endDate;

    public Events(String task, String start, String end) {
        super(task);
        this.startDate = start;
        this.endDate = end;
    }

    @Override
    public String status() {
        return isDone() ? "[E][X]" : "[E][ ]";
    }

    @Override
    public String taskName() {
        return super.taskName() + "(from: " + startDate + " to: " + endDate + ")";
    }

    @Override
    public String type() {
        return "Events";
    }
}
