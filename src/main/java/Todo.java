class Todo extends Task {
    public Todo(String description, boolean isMarked) {
        super(description); // initializes its task
        super.isMarked = isMarked;
    }

    public String writeFormat() {
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
