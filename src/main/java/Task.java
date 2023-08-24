public abstract class Task {
    protected String description;
    protected boolean isDone;
    protected String type;
    protected static int taskCount = 0;
    public abstract String getDetails();

    public Task(String description, String type) {
        this.description = description;
        this.isDone = false;
        this.type = type;
        Task.taskCount += 1;
    }

    public void addedTaskDescription() {
        System.out.println("Got it. I've added this task:");
        System.out.println("   " + this.getStatusAndDescription());
        System.out.println(String.format("Now you have %s tasks in the list.",Task.taskCount));
    }

    public String getStatusIcon() {
        return (isDone ? "X" : " ");
    }

    public String getType() {
        return this.type;

    }

    public String getStatusAndDescription() {
        return String.format("[%s][%s] %s%s",
                this.getType(),
                this.getStatusIcon(),
                this.description,
                this.getDetails());
    }

    public void markAsDone() {
        this.isDone = true;
        System.out.println("Nice! I've marked this task as done:");
        System.out.println("  " + this.getStatusAndDescription());
    }

    public void markAsNotDone() {
        this.isDone = false;
        System.out.println("OK, I've marked this task as not done yet:");
        System.out.println("  " + this.getStatusAndDescription());
    }
}
