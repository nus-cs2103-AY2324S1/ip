package extensions;

public class TodoTask extends Task {
    public TodoTask(String desc) {
        super(desc);
    }

    @Override
    public String toString() {
        String output = String.format("[T]%s", super.toString());
        return output;
    }

}
