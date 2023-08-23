public class Event extends Task{
    char type = 'E';
    public Event(String s) {
        super(s);
    }

    @Override
    public String toString() {
        return "[" + type + "]" + super.toString();
    }
}
