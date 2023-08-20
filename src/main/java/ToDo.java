public class ToDo extends Task{
    public ToDo(String description) {
        super(description);
    }

    @Override
    public String toString() {
        String ret = "[T] " + super.toString();
        return ret;
    }
}
