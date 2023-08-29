import java.util.ArrayList;

public class Deadline extends Task{

    protected String time;

    public Deadline(String description) {

        super(description.split("/by")[0]);
        this.time = description.split("/by")[1];
    }

    public String type() {
        return "D";
    }


    @Override
    public String storeText() {
        return String.format("%s|%s|%s /by %s", this.type(), this.isDone, this.description, this.time);
    }

    @Override
    public String toString() {
        String type = this.type();
        return String.format("[%s]%s(by:%s)", type, super.toString(), this.time );
    }

}
