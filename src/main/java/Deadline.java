class Deadline extends Task{

    private final String time;
    public Deadline(String name, String time) {
        super(name);
        this.time = time;
    }

    @Override
    public String getText() {
        return super.getText() + " | " + time;
    }

    @Override
    public String toString() {
        return "[D]" + super.toString()
                +  " (by: " + time + ")";
    }
}
