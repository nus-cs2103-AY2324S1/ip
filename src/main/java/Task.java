public class Task {
    protected String description;
    protected boolean isDone;
    private String horizontal = "____________________________________________________________";

    public Task(String description) {
        this.description = description;
        this.isDone = false;
    }

    public String getStatusIcon() {
        return (isDone ? "X" : " "); // mark done task with X
    }

    public String getNumber() {
        return (isDone ? "1" : "0"); // mark done task with X
    }
    public String getDescription() {
        return description;
    }

    public void silentMark() {
        this.isDone = true;
    }

    public void markAsDone() {
        this.isDone = true;

        System.out.println(horizontal);
        System.out.println("Nice! I've marked this task as done:");
        System.out.println("[" + this.getStatusIcon() + "] " + this.description);
        System.out.println(horizontal);
    };

    public void unmark() throws IllegalChatBotExceptions {
        if (!this.isDone) {
            throw new IllegalChatBotExceptions(horizontal + "\nOOPS!!! You did not mark this task.\n" + horizontal);
        }
        this.isDone = false;

        System.out.println(horizontal);
        System.out.println("OK, I've marked this task as not done yet:");
        System.out.println("[" + this.getStatusIcon() + "] " + this.description);
        System.out.println(horizontal);
    };

    @Override
    public String toString() {
        return "[" + this.getStatusIcon() +  "] " + this.description;
    }
}