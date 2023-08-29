package duke.task;

public class Deadline extends Task {
    private String time;

    public Deadline(String name, String time) {
        super(name);
        this.time = time;
    }

    @Override
    public String toSave() {
        return (super.isComplete ? "1 " : "0 ")  + "deadline " + super.name + "/by" + time;
    }

    @Override
    public String toString() {
        return "[D]" + super.toString() + "(by:" + time + ")";
    }
}
