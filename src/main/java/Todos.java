public class Todos extends ListItem{
    public Todos (String text) {
        super(text);
    }

    @Override
    public String toString() {
        return "[T] " + super.toString();
    }
}
