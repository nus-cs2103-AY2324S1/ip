package duke;

import duke.exceptions.DukeException;
import duke.exceptions.TaskParseException;
import duke.task.Deadline;
import duke.task.Event;
import duke.task.Task;
import duke.task.Todo;

/**
 * parser to make sense of user commands, parsing from command to action
 */
public class Parser {
    /**
     * if command is delete, use this method to get the index of task user wants to delete
     * @param text original command
     * @return Integer representing the index of task to be deleted
     */
    public static Integer getDeleteIndex(String text) {
        return Integer.parseInt(text.split(" ")[1]) - 1;
    }

    /**
     *  if command is mark, use this method to get the index of task user wants to mark
     *  @param text original command
     *  @return Integer representing the index of task to be marked
     */
    public static Integer getMarkIndex(String text) {
        return Integer.parseInt(text.split(" ")[1]) - 1;
    }
    /**
     *  if command is unmark, use this method to get the index of task user wants to unmark
     *  @param text original command
     *  @return Integer representing the index of task to be unmarked
     */
    public static Integer getUnmarkIndex(String text) {
        return Integer.parseInt(text.split(" ")[1]) - 1;
    }

    /**
     * given a TaskType, parses a text into a task of that type
     * @param text original user input
     * @param type type of task that user specified
     * @return task constructed according to user input
     */
    public static Task parseTask(String text, Duke.TaskType type) throws DukeException {
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

    /**
     * make sense of what TaskType the user is trying to create from the first word of their input
     * @param text user input
     * @return TaskType according to user input
     * @throws DukeException Some components of the command may be missing for certain tasks
     * @throws TaskParseException unable to understand what TaskType the user is trying to create
     */
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
