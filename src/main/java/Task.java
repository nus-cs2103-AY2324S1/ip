public class Task {
    protected String description;
    protected boolean isDone;

    public Task(String description) {
        this.description = description;
        this.isDone = false;
        System.out.println("------------------------------------------");
        System.out.println("added: " + description);
        System.out.println("------------------------------------------");
    }

    public String getStatusIcon() {
        return (isDone ? "X" : " ");
    }

    public void setStatus(boolean status) {
        this.isDone = status;

        System.out.println("------------------------------------------");
        if (isDone) {
            System.out.printf("Nice! I've marked this task as done: \n\t%s", this);
        } else {
            System.out.printf("OK, I've marked this task as not done yet: \n\t%s", this);
        }
        System.out.println("\n------------------------------------------");
    }

    @Override
    public String toString() {
        return String.format("[%s] %s", getStatusIcon(), description);
    }
}