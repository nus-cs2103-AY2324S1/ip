package parser;

import exception.BobCorruptFileException;
import exception.BobException;
import task.Deadline;
import task.Event;
import task.Task;
import task.Todo;

public class Parser {
    public Parser() {

    }
    public static Task parseStoredTask(String inputString) throws BobException {
        String[] split = inputString.split("\\|");
        String commandType = split[0];

        try {
            switch (commandType) {
                case "T":
                    return new Todo(split[2], Integer.parseInt(split[1]) == 1);
                case "D":
                    return new Deadline(split[2], split[3], Integer.parseInt(split[1]) == 1);
                case "E":
                    return new Event(split[2], split[3], split[4], Integer.parseInt(split[1]) == 1);
            }
            return null;
        } catch (Exception e) {
            throw new BobCorruptFileException("Your save file might be corrupted :(");
        }
    }
}
