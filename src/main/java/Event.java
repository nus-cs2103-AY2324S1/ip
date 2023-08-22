public class Event extends Task{
    public String begin;
    public String end;

    public Event(String name, String begin, String end) {
        super(name);
        this.begin = begin;
        this.end = end;

    }



    public String toPrint() {
        String str = "[E] " + super.getStatus() + " " + super.name
                + " (from: " + this.begin + " to: " + this.end + ")";
        return str;
    }
}
