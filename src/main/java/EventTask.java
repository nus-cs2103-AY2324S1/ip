public class EventTask extends Task {
    public EventTask(String title) {
        super(title);
    }

    @Override
    public String toString() {
        return "[E] " + super.toString();
    }
}
