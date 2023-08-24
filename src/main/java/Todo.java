public class Todo extends Task{

    public Todo(String description) {
        super(description);
    }

    @Override
    public String displayTask() {
        return (".[T]" + super.getStatusIcon() + description);
    }

    @Override
    public void addedTask() {
        System.out.println("Got it. I've added this task:");
        System.out.println("  [T]" + super.getStatusIcon() + description);
    }

    @Override
    public void displayTaskMark() {
        System.out.println("[T]" + super.getStatusIcon() + description);
    }
}
