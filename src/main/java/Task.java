public class Task {
    String task;
    boolean isDone;

    public Task(String task) throws DukeException {
        //when the command didn't go through any of the given cases
        if (task == null){
            throw new DukeException("I'm sorry, but I don't know what that means.");
        }
        this.task = task;
        this.isDone = false;
    }
    
    public String getTask() {
        return this.task;
    }

    public String getStatus() {
        String status = "[" + (isDone ? "✓" : "✗") + "]";
        return status + " " + this.getTask();
    }

    public void markDone() {
        this.isDone = true;
        System.out.println("Nice! I've marked this task as done:");
        System.out.println(this.getStatus());
        System.out.println("Here's a lollipop. 🍭");
    }

    public void markUndone() {
        this.isDone = false;
        System.out.println("OK, I've marked this task as not done yet:");
        System.out.println(this.getStatus());
        System.out.println("Undone complete.");
    }
}
