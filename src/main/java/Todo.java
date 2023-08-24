public class Todo extends Task{

    public Todo(String description, int number) {
        super(description, number);
    }

    @Override
    public void displayTask() {
        System.out.println(number + ".[T]" + super.getStatusIcon() + description);
    }

    @Override
    public void addedTask() {
        System.out.println("Got it. I've added this task:");
        System.out.println("  [T]" + super.getStatusIcon() + description);
        if (number == 1) {
            System.out.println("Now you have 1 task in the list.");
        } else {
            System.out.println("Now you have " + number + " tasks in the list.");
        }
    }

    @Override
    public void displayTaskMark() {
        System.out.println("[T]" + super.getStatusIcon() + description);
    }
}
