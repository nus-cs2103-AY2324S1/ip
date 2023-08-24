public class GenericTask extends Task{

    public GenericTask(String description, int number) {
        super(description, number);
    }

    @Override
    public void displayTask() {
        System.out.println(number + "." + super.getStatusIcon() + description);
    }

    @Override
    public void addedTask() {
        System.out.println("Psst. This task has no category, but I've still added this task:");
        System.out.println("  " + super.getStatusIcon() + description);
        if (number == 1) {
            System.out.println("Now you have 1 task in the list.");
        } else {
            System.out.println("Now you have " + number + " tasks in the list.");
        }
    }
}
