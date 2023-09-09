package Alex;

/**
 * A class that can be instantiated to represent a task that has no deadline or there is no need to specify time.
 */
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
