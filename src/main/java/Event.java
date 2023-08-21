public class Event extends Task {

    String[] inputs;
    public Event(String name) throws DukeException {
        super(name.split("/")[0]);
        this.inputs = name.split("/");
        if (this.inputs.length < 3) throw new DukeException("event has no end date!");

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
