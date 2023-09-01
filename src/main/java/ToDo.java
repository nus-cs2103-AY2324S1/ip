public class ToDo extends UserInput {

    public ToDo(String text) {
        super(text);
    }

    @Override
    public String toSaveFormat() {
        return "T" + super.toSaveFormat();
    }
    @Override
    public String toString() {
        return "[T]" + super.toString() ;
    }
}
