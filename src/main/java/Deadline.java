class Deadline extends Task {
    private String by;
    public Deadline(String description, String by) {
        super(description); // initializes its task
        this.by = by;
    }

    public String changeFormat() {
        int isDone = 0;
        if (isMarked) {
            isDone = 1;
        }
        return "D" + " | " + isDone + " | " + super.description + " | " + by;
    };

    @Override
    public String toString() {
        return "[D]" + super.toString() + " (by:" + by + ")";
    }
}