package duke;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Todo extends Task {
    private static Pattern createCommand = Pattern.compile("^todo( (?<taskName>.*))?");

    Todo(String name) {
        super(name);
    }

    Todo(boolean isDone, String name) {
        super(name, isDone);
    }

    public static Todo createTodo(String command) throws LukeException {
        Matcher matcher = createCommand.matcher(command);
        matcher.find();

        String taskName = matcher.group("taskName");
        if (taskName == null || taskName.isBlank()) {
            throw new LukeException("The description of a todo cannot be empty.");
        }

        return new Todo(taskName);
    }

    public static Todo createTodo(String[] args, boolean isDone) throws LukeException {
        if (args.length != 1) {
            throw new LukeException("Error creating Todo: Incorrect number of arguments");
        }

        return new Todo(isDone, args[0]);
    }

    @Override
    public String toSaveStr() {
        return "T" + " | " + super.toSaveStr();
    }

    @Override
    public boolean equals(Object o) {
        if (o instanceof Todo) {
            return super.equals(o);
        }

        return false;
    }

    @Override
    public String toString() {
        return "[T]" + super.toString();
    }
}
