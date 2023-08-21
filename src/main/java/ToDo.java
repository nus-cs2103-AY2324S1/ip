public class ToDo extends Task{
    public ToDo(String name) {
        super(name);
    }

    public String display() {
        if(done) {
            return "[T][X] " + this.name;
        }
        return "[T][] " + this.name;
    }
}
