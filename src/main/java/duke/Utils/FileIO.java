package duke.Utils;

import java.util.stream.Collectors;
import java.util.stream.Stream;
import java.util.ArrayList;
import java.util.regex.Pattern;
import java.time.LocalDateTime;

public class FileIO {
    private static final String DELIMETER = "-|-";
    private static final String SPLITDEL = "(-\\|-)";
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

    protected static String joinCsv(Object... str) {
        return Stream.of(str)
            .map(Object::toString)
            .collect(Collectors.joining(FileIO.DELIMETER));
    }
    
    protected static ArrayList<Task> readCsv(ArrayList<String> lines) throws InvalidFileDataException {
        ArrayList<Task> tasks = new ArrayList<>();
        for (String line : lines) {
            String[] args = line.split(FileIO.SPLITDEL);
            Task.Type type = Task.Type.of(args[0]);
            assertParams(args, type);
            switch(type) {
                case TODO:
                    tasks.add(Todo.of(args));
                    break;
                case DEADLINE:
                    tasks.add(Deadline.of(args));
                    break;
                case EVENT:
                    tasks.add(Event.of(args));
                    break;
                default:
                    throw new InvalidFileDataException();
            }
        }
        return tasks;
    }
    
    protected static void assertParams(String[] args, Task.Type type) throws InvalidFileDataException {
        if (args.length != type.param()) {
            throw new InvalidFileDataException();
        }
    }

    protected static String assertString(String input) throws InvalidFileDataException {
        if (input.equals("")) {
            throw new InvalidFileDataException();
        }
        return input;
    }

    protected static boolean assertBoolean(String input) throws InvalidFileDataException {
        if (input.equals("true")) {
            return true;
        } else if (input.equals("false")) {
            return false;
        }
        throw new InvalidFileDataException();
    }
    protected static Integer assertInteger(String input) throws InvalidFileDataException {
        try {
            if (input == "") {
                throw new InvalidFileDataException();
            }
            return Integer.parseInt(input);
        } catch (NumberFormatException e) {
            throw new InvalidFileDataException();
        }
    }

    protected static LocalDateTime assertDateTime(String input) throws InvalidFileDataException {
        if (!FileIO.DATERX.matcher(input).matches()) {
            throw new InvalidFileDataException();
        }
        String[] timeSplit = input.split(" ");
        CharSequence timeSequence = 
            timeSplit[0]
            + "T"
            + timeSplit[1]
            + ":00";
        return LocalDateTime.parse(timeSequence);
    }
}
