public class ToDo extends Task{
    ToDo(String descr) {
        super(descr);
    }

    @Override
    public String toString() {
        return "[T]" + super.toString();
    }
}
