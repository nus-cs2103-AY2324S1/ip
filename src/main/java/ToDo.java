public class ToDo extends SingleTask{
    public ToDo(String s) {
        super(s);
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
    public String remove() {
        return "OK DONE ALR removed your todo ah:\n" +
                "[T]" + "[" +this.getStatusIcon() +"] " + this.description;
    }
}
