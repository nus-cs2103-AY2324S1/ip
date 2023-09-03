public class ToDo extends Task {

    // Methods


    // toString
    @Override
    public String toString() {
        return "[T]" + super.toString();
    }

    // Constructor
    public ToDo(String description) {
        super(description);
    }
}
