import javax.sound.midi.SysexMessage;

public class Deadline extends Task{

    protected String by;

    public Deadline(String description, int number, String by) {
        super(description, number);
        this.by = by;
    }

    @Override
    public void displayTask() {
        System.out.println(number + ".[D]" + super.getStatusIcon() + description + "(by: " + by + ")");
    }

    @Override
    public void addedTask() {
        System.out.println("Got it. I've added this task:");
        System.out.println("  [D]" + super.getStatusIcon() + description + "(by: " + by + ")");
        if (number == 1) {
            System.out.println("Now you have 1 task in the list.");
        } else {
            System.out.println("Now you have " + number + " tasks in the list.");
        }
    }

    @Override
    public void displayTaskMark() {
        System.out.println("[D]" + super.getStatusIcon() + description + " (by: " + by + ")");
    }

    //make one display with different conditions:
    //public String
}
