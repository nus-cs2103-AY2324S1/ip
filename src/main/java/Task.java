class Task {
    protected final String description;
    protected boolean isDone;

    public Task(boolean done, String description) {
        this.description = description;
        this.isDone = done;
    }

    public void mark() throws DukeException {
        if (this.isDone) {
            throw new DukeException("Task already done");
        }
        this.isDone = true;
        Ui.print("Nice! I've marked this task as done:\n" + toString());
    }

    public void unmark() throws DukeException {
        if (!this.isDone) {
            throw new DukeException("Task still undone");
        }
        this.isDone = false;
        Ui.print("OK, I've marked this task as not done yet:\n" + toString());
    }

    private String getStatusIcon() {
        return (isDone ? "X" : " "); // mark done task with X
    }

    @Override
    public String toString() {
        return ("[" + this.getStatusIcon() + "] " + this.description);
    }

    public static Task parse(String text) throws DukeException {
        String[] parts = text.split("\\|");
        String first = parts[0];
        Task task;
        boolean done = parts[1].equals("1"); // 1 = done, 0 = undone
        String description = parts[2];
        switch (first) {
        case "T":
            task = new Todo(done, "todo " + description);
            break;

        case "E":
            String from = parts[3];
            String to = parts[4];
            task = new Event(done, "event " + description + " /from " + from + " /to " + to);
            break;

        case "D":
            String by = parts[3];
            task = new Deadline(done, "deadline " + description + " /by " + by);
            break;

        default:
            throw new DukeException("Unable to parse from hard drive");
        }
        return task;
    }
}
