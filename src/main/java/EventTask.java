public class EventTask extends Task {
    private String event;
    private String from;
    private String to;

    public EventTask(String task) {
        super(task);
        String[] splitsy =  task.split("/");
        event = splitsy[0];
        from = splitsy[1].split(" ", 2)[1];
        to = splitsy[2].split(" ", 2)[1];
    }


    public String toString() {
        return String.format("[E] | %s | %s | %s - %s",
                this.isDone() ? "[X]" : "[ ]",
                event, Parser.dateToString(from.trim()), Parser.dateToString(to.trim()));
    }
}
