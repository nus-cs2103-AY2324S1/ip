public abstract class Task {
    private String name;
    private boolean done;

    public Task(String name) {
        this.name = name;
        this.done = false;
    }

    public void markAsDone() {
        this.done = true;
        System.out.println("Nice! I've marked this task as done:");
        System.out.println(this);
    }

    public void markAsUndone() {
        this.done = false;
        System.out.println("OK, I've marked this task as not done yet:");
        System.out.println(this);
    }

    public String toString() {
        return String.format("[%s] %s", this.done ? "X" : " ", this.name);
    }
}
