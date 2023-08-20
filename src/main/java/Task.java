public class Task {
    private String description;
    private boolean done;

    public Task() {

    }

    public Task(String description){
        this.description = description;
        this.done = false;
    }

    public void markAsDone() {
        this.done = true;
        System.out.println("Wow! Spot has marked this task as done!");
        System.out.println("  " + this.toString());
    }

    public void markAsNotDone() {
        this.done = false;
        System.out.println("Spot will mark this task as not done yet then!");
        System.out.println("  " + this.toString());
    }

    public String getStatusIcon() {
        if (this.done) {
            return "X";
        } else {
            return " ";
        }
    }

    @Override
    public String toString() {
        return "[" + this.getStatusIcon() + "]" + this.description;
    }
}
