public class ToDos extends Task { // inheritance
    public ToDos (String description) { // constructor
        super(description);
    }

    @Override
    public String toString() { // polymorphism
        return "T | " + super.toString();
    }
}
