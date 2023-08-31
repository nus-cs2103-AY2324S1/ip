package Duke;

public class Event extends SingleTask {
    private String from;
    private String to;

    public Event(String description, String from, String to) {
        super(description);
        this.from = from;
        this.to = to;
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
        return "OK DONE ALR added your Event ah:\n" +
                "[E][" + getStatusIcon() + "] " + this.description +"(from: "+ this.from + " to: " + this.to + ")";
    }
    @Override
    public String listString() {
        return ". [E][" + getStatusIcon() + "] " + this.description +"(from: "+ this.from + " to: " + this.to + ")";
    }
    @Override
    public String remove() {
        return "OK DONE ALR removed your Event ah:\n" +
                "[E][" + getStatusIcon() + "] " + this.description +"(from: "+ this.from + " to: " + this.to + ")";
    }
    @Override
    public String toSaveString() {
        return "E|" + (this.isDone ? "1" : "0") + "|" + this.description + "|" + this.from + "|" + this.to;
    }
}

