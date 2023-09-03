package roo;

import roo.task.Deadline;
import roo.task.Event;
import roo.task.Task;
import roo.task.Todo;

public class Parse {
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

    public static Commands parse(String input) {
        Commands c;
        if (input.equals("list")) {
            c = Commands.LIST;
        } else if (input.startsWith("unmark")) {
            c = Commands.UNMARK;
        } else if (input.startsWith("mark")) {
            c = Commands.MARK;
        } else if (input.startsWith("delete")) {
            c = Commands.DELETE;
        } else if (input.startsWith("todo")) {
            c = Commands.TODO;
        } else if (input.startsWith("deadline")) {
            c = Commands.DEADLINE;
        } else if (input.startsWith("event")) {
            c = Commands.EVENT;
        } else if (input.startsWith("check")) {
            c = Commands.DATE;
        } else if (input.startsWith("clear")) {
            c = Commands.CLEAR;
        } else if (input.startsWith("find")) {
            c = Commands.FIND;
        } else if (input.startsWith("end") || input.startsWith("bye")) {
            c = Commands.END;
        } else {
            c = Commands.UNKNOWN;
        }
        return c;
    }
}
