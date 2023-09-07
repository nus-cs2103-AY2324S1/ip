public class Event extends Task {
    private String from;
    private String to;

    public Event(String task, String from, String to) throws DukeException {
        super(task);
        this.from = from;
        this.to = to;

        // if (task.isBlank() || task.isEmpty()) {
        //     throw new DukeException("The description of a Event task cannot be empty.");
        // }

        // if (from.isBlank() || from.isEmpty() || to.isBlank() || to.isEmpty()) {
        //     throw new DukeException("The time of an Event task cannot be empty.");
        // }
    }

    @Override
    public String getStatus(){
        String time = "(from: " + from + " to: " + to + ")";
        return "[Event]" + super.getStatus() + " " + time;
    }
}