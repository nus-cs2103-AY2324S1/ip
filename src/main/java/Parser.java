public class Parser {
    public static Integer getDeleteIndex(String text) {
        return Integer.parseInt(text.split(" ")[1]) - 1;
    }

    public static Integer getMarkIndex(String text) {
        return Integer.parseInt(text.split(" ")[1]) - 1;
    }

    public static Integer getUnmarkIndex(String text) {
        return Integer.parseInt(text.split(" ")[1]) - 1;
    }

    public static Task parseTask(String text, Duke.TaskType type) throws DukeException, TaskParseException {
        switch (type) {
        case TODO: {
            return new Todo(false, text);
        }

        case DEADLINE: {
            if (!text.contains("/by") || text.length() <= text.indexOf("/by") + 4) {
                throw new DukeException("You forgot to specify when the deadline ends!");
            }
            return new Deadline(false, text);
        }

        case EVENT: {
            if (!text.contains("/from")) {
                throw new DukeException("You forgot to specify when the event starts!");
            }
            if (!text.contains("/to")) {
                throw new DukeException("You forgot to specify when the event ends!");
            }
            return new Event(false, text);
        }

        default: {
            throw new DukeException("Not a task!");
        }
        }
    }
    public static Duke.TaskType parseType(String text) throws DukeException, TaskParseException {
        String[] textParts = text.split(" ");
        String firstWord = textParts[0].toLowerCase();
        if (textParts.length <= 1 && !firstWord.equals("list") && !firstWord.equals("bye")) {
            throw new DukeException("You forgot to write the task");
        }

        switch (textParts[0].toLowerCase()) {
        case "list": {
            return Duke.TaskType.LIST;
        }

        case "mark": {
            return Duke.TaskType.MARK;
        }

        case "unmark": {
            return Duke.TaskType.UNMARK;
        }
        case "deadline": {
            return Duke.TaskType.DEADLINE;
        }
        case "event": {
            return Duke.TaskType.EVENT;
        }

        case "todo": {
            return Duke.TaskType.TODO;
        }

        case "delete": {
            return Duke.TaskType.DELETE;
        }

        case "bye": {
            return Duke.TaskType.BYE;
        }

        default: {
            throw new TaskParseException("Not a TaskType");
        }
        }
    }
}
