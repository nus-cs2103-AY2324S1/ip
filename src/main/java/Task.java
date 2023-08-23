public class Task {

    private String name;
    private boolean done;

    public Task(String name) {
        this.name = name;
        this.done = false;
    }

    public void markAsDone() {
        this.done = true;
        System.out.println("\nNice! I've marked this task as done:\n  " + this + "\n");
    }

    public void unmarkAsDone() {
        this.done = false;
        System.out.println("\nNice! I've unmarked this task as done:\n  " + this + "\n");
    }

    @Override
    public String toString() {
        char markDone = this.done ? 'X' : ' ';
        return "[" + markDone + "] " + this.name;
    }

}
