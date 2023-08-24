public class ToDo extends Task{

    public ToDo(String Description) {
        super(Description);
        System.out.println("    " + this.toString());
    }

    @Override
    public String toString() {
        return "[T] " + super.toString();
    }
}
