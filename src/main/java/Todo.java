import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Todo extends Task{
    private static Pattern createCommand = Pattern.compile("^todo( (?<taskName>.*))?");

    Todo(String name) {
        super(name);
    }

    public static Todo createTodo(String command) throws LukeException{
        Matcher matcher = createCommand.matcher(command);
        matcher.find();

        String taskName = matcher.group("taskName");
        if (taskName == null || taskName.isBlank()) {
            throw new LukeException("The description of a todo cannot be empty.");
        }

        return new Todo(taskName);
    }

    @Override
    public String toString() {
        return "[T]" + super.toString();
    }
}
