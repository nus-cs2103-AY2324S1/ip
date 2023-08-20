import java.util.Arrays;
import java.util.stream.Stream;

public enum Commands {
    BYE,
    MARK,
    UNMARK,
    LIST,
    DELETE,
    TODO,
    DEADLINE,
    EVENT;

    public static Commands cmdFromString(String tag) throws UnknownCommandException {
        for (Commands cmd: Commands.values()) {
            if (cmd.toString().equals(tag.toUpperCase())) {
                return cmd;
            }
        }
        throw new UnknownCommandException();
    }
}
