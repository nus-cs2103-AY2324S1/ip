package parser;

import task.Deadline;
import task.Event;
import task.Task;
import task.Todo;

import java.util.Optional;

public class TaskParser {

    public static String formatSave(Task[] tasks) {
        StringBuilder ret = new StringBuilder();
        for (Task task : tasks) {
            ret.append(task.toSave());
            ret.append("\n");
        }
        return ret.toString().trim();
    }

    public static Optional<Task> parseSave(String saveLine) {
        String[] splitArray = saveLine.split(" \\|\\| ");
        Optional<Task> ret = Optional.empty();
        switch (splitArray[0]) {
            case "T": {
                boolean status = Integer.parseInt(splitArray[2]) >= 1;
                ret = Optional.of(new Todo(splitArray[1], status));
                break;
            }
            case "D": {
                boolean status = Integer.parseInt(splitArray[2]) >= 1;
                ret = Optional.of(new Deadline(splitArray[1], status, TimeParser.parseTime(splitArray[3])));
                break;
            }
            case "E": {
                boolean status = Integer.parseInt(splitArray[2]) >= 1;
                String[] timeDescriptor = splitArray[3].split(" to ");
                ret = Optional.of(new Event(splitArray[1], status, TimeParser.parseTime(timeDescriptor[0]), TimeParser.parseTime(timeDescriptor[1])));
                break;
            }
        }
        return ret;
    }

}
