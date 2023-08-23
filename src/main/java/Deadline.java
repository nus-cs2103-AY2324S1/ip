public class Deadline extends  Task{
    char type =  'D';
    public Deadline(String s) {
        super(s);
    }

    @Override
    public String toString() {
        return "[" + type + "]" + super.toString();
    }
}
