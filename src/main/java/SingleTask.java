public class SingleTask {
    String description;
    boolean done;

    public SingleTask(String s) {
        this.description = s;
        this.done = false;
    }

    public void mark() {
        this.done = true;
        System.out.println("Ok boy i mark for you already \n" +
                "[" +this.getStatusIcon() +"] " + this.description);

    }

    public void unmark() {
        this.done = false;
        System.out.println("Ok boy I unmark for you already \n" +
                "[" +this.getStatusIcon() +"] " + this.description);
    }
    public String getStatusIcon() {
        return (this.done ? "X" : " ");
    }
    public String listString() {
        return "";
    }

    public String remove () {
        return "";
    }
}
