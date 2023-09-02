package bot.parsers;

import bot.exception.DateTimeParseBotException;
import bot.task.Task;
import bot.task.Todo;
import bot.task.Event;
import bot.task.Deadline;

public class StorageParser {
    private static final String UNIQUE_FILE_SEPARATOR = " &##& ";
    private static final String TODO_HEADER = "[T] ";
    private static final String DEADLINE_HEADER = "[D] ";

    public static Task parseFileInput(String str) throws DateTimeParseBotException {
        String[] stringArr = str.split(StorageParser.UNIQUE_FILE_SEPARATOR);
        switch(stringArr[0]) {
        case StorageParser.TODO_HEADER:
            return new Todo(stringArr[1], stringArr[2]);
        case StorageParser.DEADLINE_HEADER:
            return new Deadline(stringArr[1], stringArr[2], stringArr[3]);
        default:
            return new Event(stringArr[1], stringArr[2], stringArr[3], stringArr[4]);
        }
    }
}
