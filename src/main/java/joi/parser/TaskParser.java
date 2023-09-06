package joi.parser;

import joi.utils.Task;
import joi.utils.Event;
import joi.utils.Deadline;
import joi.utils.ToDo;

public class TaskParser {
    public TaskParser() {

    }

    public static Task readTasksFromFile(String line) {
        String[] str = line.split("#");
        String eventType = str[0];
        String fullDesc = str[1];
        boolean status = str[2].equals("X");

        if (eventType.startsWith("event")) {
            String[] substr = fullDesc.split("@");
            String desc = substr[0];
            String start = substr[1];
            String end = substr[2];

            return new Event(desc, start, end, status);
        } else if (eventType.startsWith("deadline")) {
            String[] substr = fullDesc.split("@");
            String desc = substr[0];
            String end = substr[1];

            return new Deadline(desc, end, status);
        } else if (eventType.startsWith("todo")) {
            String[] substr = fullDesc.split("@");
            String desc = substr[0];

            return new ToDo(desc, status);
        } else {
            System.out.println("invalid file format");
            return null;
        }
    }
}
