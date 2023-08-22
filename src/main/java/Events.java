public class Events extends Task{
    private String start;
    private String end;
    public Events(String task, int taskId, String start, String end) {
        super(task, taskId);
        this.start = start;
        this.end = end;
    }

    @Override
    public String getTask() {
        String checkbox = this.done ? "[X] " : "[ ] ";
        String startTime = "(from: " + start.substring(5);
        String endTime = "to: " + end.substring(3) + ")";
        return taskId + "." + "[E]" + checkbox + task + " " + startTime + " " + endTime;
    }
    @Override
    public String checkBox() {
        String checkbox = this.done ? "[X] " : "[ ] ";
        String startTime = "(from: " + start.substring(5);
        String endTime = "to: " + end.substring(3) + ")";
        return "[E]" + checkbox + task + " " + startTime + " " + endTime;
    }
}
