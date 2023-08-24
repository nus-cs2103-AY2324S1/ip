import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Deadline extends Task {
    private static Pattern createCommand =
            Pattern.compile("^deadline ?(?<taskName>.*?)? ?(/by (?<finishByTime>.*))?$");
    private String finishByTime;
    Deadline(String name, String finishByTime) {
        super(name);
        this.finishByTime = finishByTime;
    }

    public static Deadline createDeadline(String command) throws LukeException{
        Matcher matcher = createCommand.matcher(command);
        matcher.find();

        String taskName = matcher.group("taskName");
        if (taskName == null || taskName.isBlank()) {
            throw new LukeException("The description of a todo cannot be empty.");
        }
        String finishByTime = matcher.group("finishByTime");
        if (finishByTime == null || finishByTime.isBlank()) {
            throw new LukeException("The due date (/by ...) of a deadline cannot be empty.");
        }

        return new Deadline(taskName, finishByTime);
    }

    @Override
    public String toString() {
        return "[D]" + super.toString() + " (by: " + finishByTime + ")";
    }
}
