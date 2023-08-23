public class Deadlines extends ListItem{
    String end;
    public Deadlines(String text, String end) {
        super(text);
        this.end = end;
    }

    @Override
    public String toString() {
        return "[D] " + super.toString() + String.format(" (by: %s)", this.end);
    }
}
