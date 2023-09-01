package deterministicparrot;

public class ToDo extends Task {
    ToDo(String s) {
        super(s);
    }

    @Override
    public String toString() {
        return String.format("[T]%s", super.toString());
    }
}
