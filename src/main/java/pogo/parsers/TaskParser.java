package pogo.parsers;

import pogo.tasks.TaskType;

public class TaskParser {
    public static TaskType toTaskType(String input) {
        if (input.startsWith("todo") || input.startsWith("T")) {
            return TaskType.TODO;
        } else if (input.startsWith("deadline") || input.startsWith("D")) {
            return TaskType.DEADLINE;
        } else if (input.startsWith("event") || input.startsWith("E")) {
            return TaskType.EVENT;
        } else {
            return null;
        }
    }
}
