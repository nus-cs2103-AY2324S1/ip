public class Task {
    protected String description;
    protected boolean isDone;
    protected int number;

    public Task(String description, int number) {
        this.description = description;
        this.number = number;
        this.isDone = false;
    }

    public String getStatusIcon() {
        return (isDone ? "[X]" : "[ ]");
    }

    public void mark() {
        isDone = true;
        System.out.println("Nice! I've marked this task as done:");
        display();
    }

    public void unmark() {
        isDone = false;
        System.out.println("OK, I've marked this task as not done yet:");
        display();
    }
    public void display() {
        System.out.println(number + "." + getStatusIcon() + " " + description);
    }

}
