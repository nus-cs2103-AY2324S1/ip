package bouncybob.task;

public class ToDos extends Task {
    public ToDos(String name) {
        super(name);
    }

    @Override
    public String getSymbol() {
        return "T";
    }
}
