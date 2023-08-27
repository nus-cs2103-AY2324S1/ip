public class ToDo extends Task {
    public ToDo(String name) {
        super(name);
    }

    @Override
    public String toString() {
        if (this.done) {
            return "[T][X] " + this.name;
        }

        return "[T][ ] " + this.name;
    }
}
