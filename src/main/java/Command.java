import java.util.regex.Matcher;
import java.util.regex.Pattern;

public abstract class Command {

    public Command() {
    }

    public abstract void execute(Storage storage, Ui ui, TaskList taskList);
}
