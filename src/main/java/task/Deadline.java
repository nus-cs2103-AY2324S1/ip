package task;

public class Deadline extends Task {
    private String byTime;
    public Deadline(String details, String time) {
        super(details);
        super.setTaskType("D");
        byTime = time;
    }

    @Override
    public String toString() {
        String output;
        output = super.toString() + "(" + byTime + ")";
        return output;
    }
}
