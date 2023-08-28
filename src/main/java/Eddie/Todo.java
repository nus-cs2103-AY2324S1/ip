package Eddie;

public class Todo extends Task{

    public Todo(String name) {
        super(name);
    }

    public String getType() {
        return "T";
    }
}
