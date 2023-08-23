public class ToDos extends Task{
    char type = 'T';
    public ToDos(String s) {
        super(s);
    }
    @Override
    public String toString() {
        return "[" + type + "]" + super.toString();
    }
}
