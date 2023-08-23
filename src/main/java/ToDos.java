public class ToDos extends Task{
    public ToDos(String text){
        super(text);
    }

    @Override
    public String toString() {
        return "[T]" + super.toString();
    }
}
