package phi.util;

import phi.task.Deadline;
import phi.task.Event;
import phi.task.ToDo;
import phi.ui.Ui;

/**
 * Represents the handling of user-commands and making sense of them
 */
public class Parser {
    private final TaskList tasks;
    private final Ui ui;

    public Parser(TaskList tasks) {
        this.tasks = tasks;
        this.ui = new Ui();
    }

    /**
     * Method that handles parsing user inputs into its respective commands. Helps to check for user-input errors.
     * String output will be handled by the function caller (PHI)
     *
     * @param input User-input given to PHI
     * @return      Response of the parser to the input, to be printed by PHI
     */
    public String handle(String input) {
        try {
            // Handling of Bye
            if (input.equals("bye")) {
                return ui.goodbye();
                // Handling of List
            } else if (input.startsWith("list")) {
                return handleList(input);
                // Handling of Mark
            } else if (input.startsWith("mark")) {
                return tasks.doTask(input);
                // Handling of Unmark
            } else if (input.startsWith("unmark")) {
                return tasks.undoTask(input);
                // Handling of Todo
            } else if (input.startsWith("todo")) {
                return handleTodo(input);
                // Handling of Deadline
            } else if (input.startsWith("deadline")) {
                return handleDeadline(input);
                // Handling of Event
            } else if (input.startsWith("event")) {
                return handleEvent(input);
                // Handling of Delete
            } else if (input.startsWith("delete")) {
                return tasks.deleteFromInput(input);
                // Handling of Help
            } else if (input.equals("help")) {
                return Ui.helpMsg();
                // Handling of Find
            } else if (input.startsWith("find")) {
                return handleFind(input);
            }

        } catch (IllegalArgumentException e) {
            return e.getMessage();
        }
        return ("I have no idea what you mean... Say \"help\" to see a list of all available commands");
    }

    private String handleList(String input) {
        if (!input.equals("list")) {
            return ("Nice job did you mean \"list\" coz what you gave wasn't an accepted input");
        } else {
            return tasks.printList();
        }
    }

    private String handleTodo(String input) {
        assert input.startsWith("todo"): "Invalid input for todo";
        if (input.equals("todo")) {
            return "You gotta put an actual message in...";
        } else if (!input.startsWith("todo ")) {
            return String.format("Hey nice job, did you mean \"todo %s\"...", input.substring(4));
        }
        String taskMsg = input.substring(5);
        return tasks.addTask(new ToDo(taskMsg, false));
    }

    private String handleDeadline(String input) {
        assert input.startsWith("deadline"): "Invalid input for deadline";
        if (input.equals("deadline")) {
            return "You gotta put an actual message in...";
        } else if (!input.startsWith("deadline ")) {
            return String.format("Hey genius, did you mean \"deadline %s\"...", input.substring(8));
        } else if (!input.contains("/by")) {
            return "Look at which moron didn't add a deadline with the \"/by\" flag";
        }
        int byFlag = input.indexOf("/by");
        if (byFlag == 9) {
            return "Come on you have to fill in something...";
        } else if (input.endsWith("/by") || input.endsWith("/by ")) {
            return "Hey you have to give me a deadline!";
        }
        String taskMsg = input.substring(9, byFlag - 1);
        String deadlineString = input.substring(byFlag + 4);
        return tasks.addTask(new Deadline(taskMsg, false, deadlineString));
    }

    private String handleEvent(String input) {
        assert input.startsWith("event"): "Invalid input for event";
        if (input.equals("event")) {
            return "You gotta put an actual message in...";
        } else if (!input.startsWith("event ")) {
            return String.format("Hey genius, did you mean \"event %s\"...", input.substring(5));
        } else if (!input.contains("/from") && !input.contains("/to")) {
            return "Congratulations you're the only idiot who would leave out both \"/from\" and \"/to\" flags";
        } else if (!input.contains("/from")) {
            return "You're missing a ' \"/from\" flag, halfwit";
        } else if (!input.contains("/to")) {
            return "You're missing a \"/to\" flag, dimwit";
        }
        int fromFlagStart = input.indexOf("/from");
        int fromFlagEnd = fromFlagStart + 6;
        int toFlagStart = input.indexOf("/to");
        int toFlagEnd = toFlagStart + 4;
        if (fromFlagStart > toFlagStart) {
            return "Please put the \"/from\" flag before the \"/to\" flag, thanksss";
        } else if (fromFlagStart == 6) {
            return "Come on you have to fill in something...";
        } else if (toFlagStart == fromFlagEnd) {
            return "Your \"/from\" flag can't be empty! Leave a space if you want it blank.";
        } else if (input.endsWith("/to")) {
            return "Your \"/to\" flag can't be empty! Leave a space if you want it blank.";
        }
        String taskMsg = input.substring(6, fromFlagStart - 1);
        String fromStr = input.substring(fromFlagEnd, toFlagStart - 1);
        String toStr = input.substring(toFlagEnd);
        return tasks.addTask(new Event(taskMsg, false, fromStr, toStr));
    }

    private String handleFind(String input) {
        assert input.startsWith("find"): "Invalid input for find";
        if (input.equals("find")) {
            return "You gotta put something for me to find...";
        }
        String keyword = input.substring(5);
        return tasks.findTasks(keyword);
    }
}
