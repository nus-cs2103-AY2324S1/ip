public class ToDos extends Task{
    char type = 'T';
    public ToDos(String s) throws InvalidTaskException {
        super(s);
    }
    @Override
    public String toString() {
        return "[" + type + "]" + super.toString();
    }
}
