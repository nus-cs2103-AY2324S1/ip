package roo;

import java.util.ArrayList;

import roo.commands.Clear;
import roo.commands.Command;
import roo.commands.DeadlineCommand;
import roo.commands.Delete;
import roo.commands.End;
import roo.commands.EventCommand;
import roo.commands.Find;
import roo.commands.ListCommand;
import roo.commands.ListDate;
import roo.commands.Mark;
import roo.commands.Tag;
import roo.commands.TodoCommand;
import roo.commands.Unknown;
import roo.commands.Unmark;
import roo.commands.UnTag;
import roo.task.Deadline;
import roo.task.Event;
import roo.task.Task;
import roo.task.Todo;

/**
 * Deals with making sense of the user command.
 */
public class Parse {
    /**
     * Creates a Task object based on the inputs[0 string.
     * @param str The inputs[0 string representing a task.
     * @return A Task object parsed from the inputs[0 string, or null if parsing fails.
     */
    public static Task makeTask(String str) {
        String[] inputs = str.substring(0, str.length() - 1).split("#");
        ArrayList<String> tags = new ArrayList<>();
        if (inputs.length > 0) {
            for(int i = 1; i < inputs.length; i++) {
                tags.add("#" + inputs[i].replace(" ", ""));
            }
        }

        if (str.startsWith("[T]")) {
            return Parse.createTodo(inputs[0].split(" tags:")[0], tags);
        } else if (str.startsWith("[D]")) {
            if (str.contains("tags:")) {
                return Parse.createDeadline(inputs[0].split(" tags:")[0], tags);
            } else {
                return Parse.createDeadline(str, tags);
            }
        } else if (str.startsWith("[E]")) {
            if (str.contains("tags:")) {
                return Parse.createEvent(inputs[0].split(" tags:")[0], tags);
            } else {
                return Parse.createEvent(str, tags);
            }
        } else {
            return null;
        }
    }

    private static Todo createTodo(String str, ArrayList<String> tags) {
        try {
            if (str.contains("[x]")) {
                return new Todo(str.substring(7), true, tags);
            } else if (str.contains("[ ]")) {
                return new Todo(str.substring(7), false, tags);
            }
        } catch (RooException e) {
            System.err.println(e.getMessage());
        }
        return null;
    }

    private static Deadline createDeadline(String str, ArrayList<String> tags) {
        try {
            int sub = str.indexOf("by: ");
            if (str.contains("[x]")) {
                return new Deadline(str.substring(7, sub - 1), str.substring(sub + 4), true, tags);
            } else if (str.contains("[ ]")) {
                return new Deadline(str.substring(7, sub - 1), str.substring(sub + 4), false, tags);
            }
        } catch (RooException e) {
            System.err.println(e.getMessage());
        }
        return null;
    }

    private static Event createEvent(String str, ArrayList<String> tags) {
        try {
            int sub1 = str.indexOf("from: ");
            int sub2 = str.indexOf("to: ");
            if (str.contains("[x]")) {
                return new Event(str.substring(7, sub1 - 1), str.substring(sub1 + 6, sub2 - 1),
                        str.substring(sub2 + 4), true, tags);
            } else if (str.contains("[ ]")) {
                return new Event(str.substring(7, sub1 - 1), str.substring(sub1 + 6, sub2 - 1),
                        str.substring(sub2 + 4), false, tags);
            }
        } catch (RooException e) {
            System.err.println(e.getMessage());
        }
        return null;
    }

    /**
     * Parses a user inputs[0 string and determines the corresponding command.
     * @param input The user inputs[0 string.
     * @return The Command enum representing the detected command.
     */
    public static Command parse(String input) {
        String[] inputs = input.split("#");
        ArrayList<String> tags = new ArrayList<>();
        if (inputs.length > 0) {
            for(int i = 1; i < inputs.length; i++) {
               tags.add("#" + inputs[i]);
            }
        }
        if (inputs[0].equals("list")) {
            return new ListCommand();
        } else if (inputs[0].startsWith("unmark")) {
            return new Unmark(inputs[0]);
        } else if (inputs[0].startsWith("mark")) {
            return new Mark(inputs[0]);
        } else if (inputs[0].startsWith("delete") || inputs[0].startsWith("remove")) {
            return new Delete(inputs[0]);
        } else if (inputs[0].startsWith("todo")) {
            return new TodoCommand(inputs[0], tags);
        } else if (inputs[0].startsWith("deadline")) {
            return new DeadlineCommand(inputs[0], tags);
        } else if (inputs[0].startsWith("event")) {
            return new EventCommand(inputs[0], tags);
        } else if (inputs[0].startsWith("check")) {
            return new ListDate(inputs[0]);
        } else if (inputs[0].startsWith("clear")) {
            return new Clear();
        } else if (inputs[0].startsWith("find")) {
            return new Find(input);
        } else if (inputs[0].startsWith("tag")) {
            return new Tag(inputs[0], tags);
        } else if (inputs[0].startsWith("untag")) {
            return new UnTag(inputs[0]);
        } else if (inputs[0].startsWith("end") || inputs[0].startsWith("bye")) {
            return new End();
        } else {
            return new Unknown();
        }
    }
}
