public class Task {
    private String name;
    private boolean completed;

    public Task(String name) {
        this.name = name;
        this.completed = false;
    }

    //getters & setters
    public String getName() {
        return this.name;
    }

    public boolean getCompleted() {
        return this.completed;
    }

    public void setCompleted() {
        this.completed = true;
        System.out.printf("Nice! I've marked this task as done: \n");
        System.out.println(Duke.doneCheckbox + this.getName());
    }

    public void setUncompleted() {
        this.completed = false;
        System.out.println("OK, I've marked this task as not done yet: ");
        System.out.println(Duke.undoneCheckbox + this.getName());
    }

    public String getStatus() {
        if (this.completed) {
            //completed
            return Duke.doneCheckbox + this.getName();
        } else {
            //not completed
            return Duke.undoneCheckbox + this.getName();
        }
    }
}
