public class Event extends Task{
    public String begin;
    public String end;

    public Event(String name, String begin, String end, String isDone) {
        super(name, isDone);
        this.begin = begin;
        this.end = end;
    }

    @Override
    public String toDataString() {
        return super.toDataString() + " | " + begin + " | " + end;
    }

    @Override
    public String toString() {
        String str = "[E] " + super.getStatus() + " " + super.name
                + " (from: " + this.begin + " to: " + this.end + ")";
        return str;
    }
}
