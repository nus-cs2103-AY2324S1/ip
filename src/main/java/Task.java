public class Task {
    protected String description;
    protected boolean isDone;

    public Task(String description) {
        this.description = description;
        this.isDone = false;

    }

    public String getStatusIcon() {
        return (isDone ? "X" : " ");
    }

    public void setStatus(boolean status) {
        this.isDone = status;

        if (isDone) {
            System.out.printf("Nice! I've marked this task as done:\n\t%s\n", this);
        } else {
            System.out.printf("OK, I've marked this task as not done yet:\n\t%s\n", this);
        }
    }

    @Override
    public String toString() {
        return String.format("[%s] %s", getStatusIcon(), description);
    }
}