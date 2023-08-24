class Task {
    String description; // the toString handles the space after the [ ] or [X]
    boolean isDone = false;
    public Task(String description) {
        this.description = description;
    }

    @Override
    public String toString() {
        return isDone ? "[X] " + this.description : "[ ] " + this.description;
    }

    public boolean isDone() {
        return this.isDone;
    }

    public void unmark() {
        this.isDone = false;
    }

    public void mark() {
        this.isDone = true;
    }

    public String addTaskString (int currNumOfTask) {
        return "Got it. I've added this task:\n  "
                + this.toString() + "\n" // not sure if this toString will call this toString or the object's toString
                + "Now you have " + currNumOfTask + " tasks in the list.\n";
    }
}
