class Event extends Task {
    final String from;
    final String to;
    public Event(boolean done, String desc) throws DukeException {
        super(done, desc.substring(6, desc.indexOf("/from")));
        int fromIndex = desc.indexOf("/from");
        int toIndex = desc.indexOf("/to");
        try {
            this.from = desc.substring(fromIndex + 6, toIndex);
            this.to = desc.substring(toIndex + 4);
        } catch (StringIndexOutOfBoundsException e) {
            throw new DukeException("You didn't enter the event in the correct format!");
        }
        if (from.isEmpty() || to.isEmpty()) {
            throw new DukeException("One of the fields are empty!");
        }
    }

    @Override
    public String toString() {
        return "[E]" + super.toString() + "(from: " + this.from + "to: " + this.to + ")";
    }
}
