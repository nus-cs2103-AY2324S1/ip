package duck.task;

import duck.DuckException;

public class TodoTask extends Task {
    public TodoTask(String name, boolean isDone) {
        super(name, isDone);
    }

    @Override
    public String stringify() {
        return "T" + super.stringify();
    }

    @Override
    public String toString() {
        return "[T]" + super.toString();
    }

    public static TodoTask parse(String fileLine) throws DuckException {
        boolean isDone = fileLine.charAt(1) == '1';
        int slashIndex = fileLine.indexOf("/");
        String name = fileLine.substring(slashIndex + 1);
        return new TodoTask(name, isDone);
    }
}
