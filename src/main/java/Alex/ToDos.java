package Alex;

public class ToDos extends Task{
    public ToDos(String description) throws AlexException {
        super(description);
    }

    @Override
    public String toString() {
        String str = "[T]" + super.toString();
        return str;
    }
}
