class Todo extends Task {
    public Todo(String desc) throws DukeException {
        super(desc.substring(5));
        if (desc.substring(5).isEmpty()) {
            throw new DukeException("You forgot to enter the task!");
        }
    }

    @Override
    public String toString() {
        return "[T]" + super.toString();
    }
}