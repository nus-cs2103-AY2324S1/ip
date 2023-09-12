package roo;

import roo.commands.Clear;
import roo.commands.Command;
import roo.commands.DeadlineCommand;
import roo.commands.Delete;
import roo.commands.End;
import roo.commands.EventCommand;
import roo.commands.Find;
import roo.commands.List;
import roo.commands.ListDate;
import roo.commands.Mark;
import roo.commands.TodoCommand;
import roo.commands.Unknown;
import roo.commands.Unmark;
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
        if (str.startsWith("[T]")) {
            return Parse.createTodo(str);
        } else if (str.startsWith("[D]")) {
            return Parse.createDeadline(str);
        } else if (str.startsWith("[E]")) {
            return Parse.createEvent(str);
        } else {
            return null;
        }
    }

    private static Todo createTodo(String str) {
        try {
            if (str.contains("[x]")) {
                return new Todo(str.substring(8), true);
            } else if (str.contains("[ ]")) {
                return new Todo(str.substring(8), false);
            }
        } catch (RooException e) {
            System.err.println(e.getMessage());
        }
        return null;
    }

    private static Deadline createDeadline(String str) {
        try {
            int sub = str.indexOf("by: ");
            if (str.contains("[x]")) {
                return new Deadline(str.substring(8, sub - 1), str.substring(sub + 4), true);
            } else if (str.contains("[ ]")) {
                return new Deadline(str.substring(8, sub - 1), str.substring(sub + 4), false);
            }
        } catch (RooException e) {
            System.err.println(e.getMessage());
        }
        return null;
    }

    private static Event createEvent(String str) {
        try {
            int sub1 = str.indexOf("from: ");
            int sub2 = str.indexOf("to: ");
            if (str.contains("[x]")) {
                return new Event(str.substring(8, sub1 - 1), str.substring(sub1 + 6, sub2 - 1),
                        str.substring(sub2 + 4), true);
            } else if (str.contains("[ ]")) {
                return new Event(str.substring(8, sub1 - 1), str.substring(sub1 + 6, sub2 - 1),
                        str.substring(sub2 + 4), false);
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
