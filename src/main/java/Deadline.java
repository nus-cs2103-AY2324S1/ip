import javax.sound.midi.SysexMessage;

public class Deadline extends Task{

    protected String by;

    public Deadline(String description, String by) {
        super(description);
        this.by = by;
    }

    @Override
    public String displayTask() {
        return (".[D]" + super.getStatusIcon() + description + "(by: " + by + ")");
    }

    @Override
    public void addedTask() {
        System.out.println("Got it. I've added this task:");
        System.out.println("  [D]" + super.getStatusIcon() + description + "(by: " + by + ")");
    }

    @Override
    public void displayTaskMark() {
        System.out.println("[D]" + super.getStatusIcon() + description + " (by: " + by + ")");
    }

    //make one display with different conditions:
    //public String
}
