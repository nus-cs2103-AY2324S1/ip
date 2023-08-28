public class Event extends Task{
    private String start;
    private String deadline;
    public Event(String task, String start, String deadline) {
        super(task);
        this.start = start;
        this.deadline = deadline;
    }

    @Override
    public String saveString() {
        String completedString = completed ? "X|" : " |";
        return "E|" + completedString + task + "|" + start + "|" + deadline;
    }

    @Override
    public String toString() {
        return "[E]" + super.toString() + " (from: " + this.start + " to: " + this.deadline + ")";
    }
}
