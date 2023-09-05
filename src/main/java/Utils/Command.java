package Utils;

import java.util.Arrays;
import java.util.stream.Collectors;
import java.lang.NumberFormatException;

public class Command {
    enum Type {
        NONE,
        INTEGER,
        STRING
    }

    private static String getArg(String input, String targetArg) {
        String[] args = input.split("/");
        for (String arg : args) {
            String[] words = arg.split(" ");
            String argName = words[0];
            if (argName.equals(targetArg)) {
                return Arrays.stream(words).skip(1).collect(Collectors.joining(" "));
            }
        }
        return "";
    }

    public static String assertString(String input, String argName) throws InvalidArgumentException {
        String arg = Command.getArg(input, argName);
        if (arg == "") {
            throw new InvalidArgumentException(argName, Type.STRING);
        }
        return arg;
    }

    public static Integer assertInteger(String input, String argName) throws InvalidArgumentException {
        try {
            String arg = Command.getArg(input, argName);
            if (arg == "") {
                throw new InvalidArgumentException(argName, Type.INTEGER);
            }
            return Integer.parseInt(arg);
        } catch (NumberFormatException e) {
            throw new InvalidArgumentException(argName, Type.INTEGER);
        }
    }
}
