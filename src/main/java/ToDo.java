public class ToDo extends Task {

    public ToDo(String name) {
        super(name);
    }



    public String toPrint() {
        String str = "[T] " + super.getStatus() + " " + super.name;
        return str;
    }



}
