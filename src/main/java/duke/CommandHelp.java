package duke;

public class CommandHelp {
    public CommandHelp() {}
    public String help(String s) throws DukeException {
        System.out.println(s);
        String[] parts = s.split(" ");
        String HelpMsg = "";
        System.out.println("balls");
        if (parts.length < 2) {
            System.out.println("here");
            HelpMsg += getCommandList();
        } else {
            HelpMsg += getCommandHelp(parts[1].toLowerCase());
        }
        return HelpMsg;
    }

    private String getCommandList() {
        return "Here is a list of the commands:\n 1.list \n 2.mark \n 3.unmark \n 4.find \n" +
                "5.todo\\event\\deadline\n Type these commands together like \n help mark";
    }

    private String getCommandHelp(String command) {
        switch (command) {
            case "delete":
                return "Can use this command to delete a task you have added by adding the task number at the end Like this: delete 1";
            case "list":
                return "Use this command to list down all current tasks";
            case "mark":
                return "Use to mark your tasks as done";
            case "unmark":
                return "Use to unmark your tasks as done";
            case "find":
                return "Use this to find a task that you have saved. \n You can write in some keywords for example \n" +
                        "find book would find all tasks that have the word book in it";
            case "todo":
                return "Use this command to create a todo task in this format:\n" +
                        "todo read book";
            case "deadline":
                return "Use this command to create a Deadline task in this format:\n" +
                        "deadline return book /by 2/12/2019 1800";
            case "event":
                return "Use this command to create a event task in this format:\n" +
                        "event project meeting /from Mon 2pm /to 4pm";
            default:
                return "Dont think you formatted correctly eh.";
        }
    }
}


