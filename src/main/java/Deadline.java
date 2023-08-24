import java.util.regex.Matcher;

public class Deadline extends Task {
    private String finishByTime;
    Deadline(String name, String finishByTime) {
        super(name);
        this.finishByTime = finishByTime;
    }

    Deadline(Matcher matcher) {
        this(matcher.group("taskName"), matcher.group("finishByTime"));
    }

    @Override
    public String toString() {
        return "[D]" + super.toString() + " (by: " + finishByTime + ")";
    }
}
