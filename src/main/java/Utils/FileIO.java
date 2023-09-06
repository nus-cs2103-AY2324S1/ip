package Utils;

import java.util.stream.Collectors;
import java.util.stream.Stream;
import java.util.ArrayList;

public class FileIO {
    private static final String DELIMETER = "-|-";
    private static final String SPLITDEL = "(-\\|-)";

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
}
