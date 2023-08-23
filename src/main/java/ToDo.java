public class ToDo extends UserInput {

    public ToDo(String text) {
        super(text);
    }

    @Override
    public String toString() {
        return "[T]" + super.toString() ;
    }
}
