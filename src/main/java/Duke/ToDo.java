package Duke;

import Duke.SingleTask;

public class ToDo extends SingleTask {
    public ToDo(String s) {
        super(s);
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
        return "OK DONE ALR added your todo ah:\n" +
                "[T]" + "[" +this.getStatusIcon() +"] " + this.description;

    }
    @Override
    public String listString() {
        return ". [T]" + "[" +this.getStatusIcon() +"] " + this.description;
    }
    @Override
    public String remove() {
        return "OK DONE ALR removed your todo ah:\n" +
                "[T]" + "[" +this.getStatusIcon() +"] " + this.description;
    }

    @Override
    public String toSaveString() {
        return "T|" + (this.isDone ? "1" : "0") + "|" + description;
    }
}
