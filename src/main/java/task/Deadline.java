package task;
import exception.KoraException;
public class Deadline extends Task {
    private String byTime;
    public Deadline(String details, String time) {
        super(details);
        super.setTaskType(TaskType.D.toString());
        byTime = time;
    }

    @Override
    public String toString() {
        String output;
        output = super.toString() + "(" + byTime + ")";
        return output;
    }
}
