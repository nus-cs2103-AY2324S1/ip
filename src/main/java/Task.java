public class Task {
    protected String description;
    protected boolean isDone;
    protected int number;

    public Task(String description, int number) {
        this.description = description;
        this.number = number;
        this.isDone = false;
    }

    public String getStatusIcon() { //for tasks
        return (isDone ? "[X]" : "[ ]");
    }

    public void mark() { //remove number and cannot mark done as done.
        if (isDone == false) {
            isDone = true;
            System.out.println("Nice! I've marked this task as done:");
            displayTaskMark();
        } else {
            System.out.println("Haha nice going... This task is already done, bozo!");
        }

    }

    public void unmark() { //remove number
        if (isDone == true) {
            isDone = false;
            System.out.println("OK, I've marked this task as not done yet:");
            displayTaskMark();
        } else {
            System.out.println("Hey... this task was never done in the first place!");
        }

    }
    public void displayTask() { //for listing out tasks
    }

    public void addedTask() { //display message for added tasks!
    }

    public void displayTaskMark() {
    }



}
