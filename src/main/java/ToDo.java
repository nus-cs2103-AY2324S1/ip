public class ToDo extends Task {

    public ToDo(String des){
        super(des);
    }

    @Override
    public String toString() {
        return "[T]" + super.toString();
    }
}
