package Utils;

import java.util.Arrays;
import java.util.regex.Pattern;
import java.util.stream.Collectors;
import java.lang.NumberFormatException;
import java.time.LocalDateTime;

public class Command {
    private static final Pattern DATERX = Pattern.compile(
        "^"
        + "("
        + "((2000|2400|2800|(19|2[0-9])(0[48]|[2468][048]|[13579][26]))-02-29)" 
        + "|(((19|2[0-9])[0-9]{2})-02-(0[1-9]|1[0-9]|2[0-8]))"
        + "|(((19|2[0-9])[0-9]{2})-(0[13578]|10|12)-(0[1-9]|[12][0-9]|3[01]))" 
        + "|(((19|2[0-9])[0-9]{2})-(0[469]|11)-(0[1-9]|[12][0-9]|30))"
        + ")"
        + "\\s"
        + "([01]?[0-9]|2[0-3]):[0-5][0-9]"
        + "$"
      ); // YYYY-MM-DD HH:mm
    
    enum Type {
        NONE,
        INTEGER,
        STRING,
        DATETIME
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

    protected static String assertString(String input, String argName) throws InvalidArgumentException {
        String arg = Command.getArg(input, argName);
        if (arg == "") {
            throw new InvalidArgumentException(argName, Type.STRING);
        }
        return arg;
    }

    protected static Integer assertInteger(String input, String argName) throws InvalidArgumentException {
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

    protected static LocalDateTime assertDateTime(String input, String argName) throws InvalidArgumentException {
        String arg = Command.getArg(input, argName);
        System.out.println(arg  + '|');
        if (!Command.DATERX.matcher(arg).matches()) {
            throw new InvalidArgumentException(argName, Type.DATETIME);
        }
        String[] timeSplit = arg.split(" ");
        CharSequence timeSequence = 
            timeSplit[0]
            + "T"
            + timeSplit[1]
            + ":00";
        return LocalDateTime.parse(timeSequence);
    }
}
