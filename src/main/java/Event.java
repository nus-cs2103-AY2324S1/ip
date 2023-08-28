public class Event extends Task{
    private final String descr;

    public Event(String descr) {
        super(descr.split("/")[0]);
        this.descr = descr;
    }

    public String breakdown(String descr) {
        String[] descrArr = descr.split("/"); //you get 0: taskName, 1: start, 2: end

        String start = descrArr[1];
        String[] parts = start.split(" ");
        String from = parts[0];
        String restOfFrom = start.substring(from.length()).trim();

        String end = descrArr[2];
        String[] parts2 = end.split(" ");
        String to = parts2[0];
        String restOfTo = end.substring(to.length()).trim();

        return "(from: " + restOfFrom + " to: " + restOfTo + ")";
    }

    @Override
    public String toString() {
        return "[E]" + super.toString() + breakdown(this.descr);
    }
}
//project meeting /from Mon 2pm /to 4pm