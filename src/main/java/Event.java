public class Event extends Task {

    String[] inputs;
    public Event(String name) {
        super(name.split("/")[0]);
        this.inputs = name.split("/");
    }

    @Override
    public String toString() {
        String fromDate = this.inputs[1];
        String toDate = this.inputs[2];
        String fromDateNoFrom = fromDate.substring(5);
        String toDateNoTo = toDate.substring(3);

        return "[E]" + super.toString() + "(from: " + fromDateNoFrom  + "to: " + toDateNoTo + ")";
    }
}
