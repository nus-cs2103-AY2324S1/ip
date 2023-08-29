public class Deadline extends SingleTask {
    String by;
    public Deadline(String description, String deadline) {
        super(description);
        this.by = deadline;
    }

    public void mark() {
        this.isDone = true;
        System.out.println("Ok boy i mark for you already \n" +
                "[" +this.getStatusIcon() +"] " + this.description);

    }
    public String getStatusIcon() {
        return (this.isDone ? "X" : " ");
    }
    public void unmark() {
        this.isDone = false;
        System.out.println("Ok boy I unmark for you already \n" +
                "[" +this.getStatusIcon() +"] " + this.description);
    }

    @Override
    public String toString() {
        return "OK DONE ALR added your Deadline ah:\n" +
                "[D][" + getStatusIcon() + "] " + this.description +"(by: "+ this.by + ")";
    }
    @Override
    public String listString() {
        return ". [D][" + getStatusIcon() + "] " + this.description +"(by: "+ this.by + ")";
    }
    @Override
    public String remove() {
        return "OK DONE ALR removed your Deadline ah:\n" +
                "[D][" + getStatusIcon() + "] " + this.description +"(by: "+ this.by + ")";
    }
    @Override
    public String toSaveString() {
        return "D|" + (this.isDone ? "1" : "0") + "|" + this.description + "|" + this.by;
    }
}
