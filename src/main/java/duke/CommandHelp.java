package duke;

public class CommandHelp {
    public CommandHelp() {}
    public String help(String s) throws DukeException {
        System.out.println(s);
        String[] parts = s.split(" ");
        String HelpMsg = "";
        if (parts.length < 2) {
            HelpMsg += "Here is a list of the commands:\n 1.list \n 2.mark \n 3.unmark \n 4.find \n" +
                    "5.todo\\event\\deadline\n Type these commands together like \n !help mark";
        } else if (s.matches(".*\\bdelete\\b.*")) {
            HelpMsg += "Can use this command to delete a task you have added by adding the task number at the end Like this:";
            HelpMsg += "delete 1";
        } else if (s.matches(".*\\blist\\b.*")) {
            HelpMsg += "Use this command to list down all current tasks";
        } else if (s.matches(".*\\bmark\\b.*")) {
            HelpMsg += "Use to mark your tasks as done";
        } else if (s.matches(".*\\bunmark\\b.*")) {
            HelpMsg += "Use to unmark your tasks as done";
        } else if (s.matches(".*\\bfind\\b.*")) {
            HelpMsg += "Use this to find a task that you have saved. \n You can write in some keywords for example \n" +
                    "find book would find all tasks that have the word book in it";
        } else if (s.matches("(?i)^\\s*!help\\s+(todo|event|deadline)\\b.*")) {
            String TypeOfEvent = parts[1].toLowerCase();
            switch (TypeOfEvent) {
                case "todo":
                    HelpMsg += "Use this command to create a todo task in this format:\n" +
                            "todo read book";
                    return HelpMsg;
                case "deadline":
                    HelpMsg += "Use this command to create a Deadline task in this format:\n" +
                            "deadline return book /by 2/12/2019 1800";
                    return HelpMsg;
                case "event":
                    HelpMsg += "Use this command to create a event task in this format:\n" +
                            "event project meeting /from Mon 2pm /to 4pm";
                    return HelpMsg;
                default:
            }
        }
        return HelpMsg;
    }
}


