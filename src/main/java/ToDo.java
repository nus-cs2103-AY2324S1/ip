public class ToDo extends Task{
    public ToDo(String str) {
        super(str);
    }

    public String toString() {
        return "[T]" + super.toString();
    }

    public String toStringId() {
        return super.getId() + "." + this;
    }
}
