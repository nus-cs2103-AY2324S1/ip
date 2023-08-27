class Task {

    protected final String description;
    protected boolean isDone;

    public Task(String description) {
        this.description = description;
        this.isDone = false;
    }

    public void mark() throws DukeException {
        if (this.isDone) {
            throw new DukeException("Task already done");
        }
        this.isDone = true;
    }

    public void unmark() throws DukeException {
        if (!this.isDone) {
            throw new DukeException("Task still undone");
        }
        this.isDone = false;
    }

    private String getStatusIcon() {
        return (isDone ? "X" : " "); // mark done task with X
    }

    @Override
    public String toString() {
        return ("[" + this.getStatusIcon() + "] " + this.description);
    }
}