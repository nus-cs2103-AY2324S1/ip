import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Deadline extends Task {
    private static Pattern createCommand =
            Pattern.compile("^deadline ?(?<taskName>.*?)? ?(/by (?<finishByTime>.*))?$");
    private String finishByTime;
    Deadline(String name, String finishByTime) {
        super(name);
        this.finishByTime = finishByTime.trim();
    }
    Deadline(boolean isDone, String name, String finishByTime) {
        super(name, isDone);
        this.finishByTime = finishByTime.trim();
    }

    public static Deadline createDeadline(String command) throws LukeException {
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

    public static Deadline createDeadline(String[] args, boolean isDone) throws LukeException {
        if (args.length != 2) {
            throw new LukeException("Error creating Deadline: Incorrect number of arguments");
        }

        return new Deadline(isDone, args[0], args[1]);
    }

    @Override
    public String toSaveStr() {
        return "D"
                + " | " + super.toSaveStr()
                + " | " + finishByTime;
    }

    @Override
    public String toString() {
        return "[D]" + super.toString() + " (by: " + finishByTime + ")";
    }
}
