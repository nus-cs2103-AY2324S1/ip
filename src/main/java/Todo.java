class Todo extends Task {
    public Todo(String description) {
        super(description); // initializes its task
    }

    public String changeFormat() {
        int isDone = 0;
        if (isMarked) {
            isDone = 1;
        }
        return "T" + " | " + isDone + " | " + super.description;
    };

    @Override
    public String toString() {
        return "[T]" + super.toString();
    }
}
