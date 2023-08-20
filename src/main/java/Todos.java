public class Todos extends Task{
    public Todos(String name) {
        super(name);
    }

    @Override
    public String toString() {
        return String.format("[T] %s %s", super.getMarking(), super.name);
    }
}
