package roo;

import roo.commands.*;
import roo.task.Deadline;
import roo.task.Event;
import roo.task.Task;
import roo.task.Todo;

/**
 * Deals with making sense of the user command.
 */
public class Parse {
    /**
     * Creates a Task object based on the input string.
     * @param str The input string representing a task.
     * @return A Task object parsed from the input string, or null if parsing fails.
     */
    public static Task makeTask(String str) {
        try {
            if (str.startsWith("[T]")) {
                if (str.contains("[x]")) {
                    return new Todo(str.substring(8), true);
                } else if (str.contains("[ ]")) {
                    return new Todo(str.substring(8), false);
                }
            } else if (str.startsWith("[D]")) {
                int sub = str.indexOf("by: ");
                if (str.contains("[x]")) {
                    return new Deadline(str.substring(8, sub - 1), str.substring(sub + 4), true);
                } else if (str.contains("[ ]")) {
                    return new Deadline(str.substring(8, sub - 1), str.substring(sub + 4), false);
                }
            } else if (str.startsWith("[E]")) {
                int sub1 = str.indexOf("from: ");
                int sub2 = str.indexOf("to: ");
                if (str.contains("[x]")) {
                    return new Event(str.substring(8, sub1 - 1), str.substring(sub1 + 6, sub2 - 1),
                            str.substring(sub2 + 4), true);
                } else if (str.contains("[ ]")) {
                    return new Event(str.substring(8, sub1 - 1), str.substring(sub1 + 6, sub2 - 1),
                            str.substring(sub2 + 4), false);
                }
            }
        } catch (RooException e) {
            System.err.println(e.getMessage());
        }
        return null;
    }

    /**
     * Parses a user input string and determines the corresponding command.
     * @param input The user input string.
     * @return The Command enum representing the detected command.
     */
    public static Command parse(String input) {
        if (input.equals("list")) {
            return new List();
        } else if (input.startsWith("unmark")) {
            return new Unmark(input);
        } else if (input.startsWith("mark")) {
            return new Mark(input);
        } else if (input.startsWith("delete") || input.startsWith("remove")) {
            return new Delete(input);
        } else if (input.startsWith("todo")) {
            return new TodoCommand(input);
        } else if (input.startsWith("deadline")) {
            return new DeadlineCommand(input);
        } else if (input.startsWith("event")) {
            return new EventCommand(input);
        } else if (input.startsWith("check")) {
            return new ListDate(input);
        } else if (input.startsWith("clear")) {
            return new Clear();
        } else if (input.startsWith("find")) {
            return new Find(input);
        } else if (input.startsWith("end") || input.startsWith("bye")) {
            return new End();
        } else {
            return new Unknown();
        }
    }
}
