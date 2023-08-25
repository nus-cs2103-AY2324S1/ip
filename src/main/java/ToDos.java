public class ToDos extends Task{
    public ToDos(String description) {
        super(description);
    }

    @Override
    public String toString() {
        String str = "[T]" + super.toString();
        return str;
    }
}
