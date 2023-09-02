package phi;

/**
 * Represents the handling of user-commands and making sense of them
 */
public class Parser {
    private final TaskList tasks;

    public Parser(TaskList tasks) {
        this.tasks = tasks;
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
            if (input.startsWith("list")) {
                if (!input.equals("list")) {
                    return ("Nice job did you mean \"list\" coz what you gave wasn't an accepted input");
                } else {
                    return tasks.printList();
                }

            } else if (input.startsWith("mark")) {
                return tasks.doTask(input);
            } else if (input.startsWith("unmark")) {
                return tasks.undoTask(input);

            // Handling of Todo
            } else if (input.startsWith("todo")) {
                if (input.equals("todo")) {
                    return "You gotta put an actual message in...";
                } else if (!input.startsWith("todo ")) {
                    return String.format("Hey nice job, did you mean \"todo %s\"...", input.substring(4));
                }
                String taskMsg = input.substring(5);
                return tasks.addTask(new ToDo(taskMsg, false));

            // Handling of Deadline
            } else if (input.startsWith("deadline")) {
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

            } else if (input.startsWith("event")) {
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

            } else if (input.startsWith("delete")) {
                return tasks.deleteTask(input);

            } else if (input.equals("help")) {
                return Ui.helpMsg();
            }
        }
        catch (IllegalArgumentException e) {
            return e.getMessage();
        }
        return("SIKE I can't process that! Try again or say \"help\" to see a list of all available commands");
    }
}
