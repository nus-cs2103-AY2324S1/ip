import java.util.regex.Matcher;

public class Todo extends Task{
    Todo(String name) {
        super(name);
    }

    Todo(Matcher matcher) {
        this(matcher.group("taskName"));
    }

    @Override
    public String toString() {
        return "[T]" + super.toString();
    }
}
