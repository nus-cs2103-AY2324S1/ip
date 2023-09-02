package Alex;

public class Parser {
    public static Command parse(String command) {
        int commandLength = command.length();
        Command c;
        if (commandLength >= 4 && command.substring(0, 4).equals("mark")) {
            Edit edit = Edit.MARK;
            c = new EditCommand(command, edit);
        } else if (commandLength >= 6 && command.substring(0, 6).equals("unmark")) {
            Edit edit = Edit.UNMARK;
            c = new EditCommand(command, edit);
        } else if (commandLength >= 6 && command.substring(0, 6).equals("delete")) {
            Edit edit = Edit.DELETE;
            c = new EditCommand(command, edit);
        } else if (command.equals("list")) {
            View view = View.LISTALL;
            c = new ViewCommand(command, view);
        } else if(commandLength == 10 && command.matches("\\d{4}-\\d{2}-\\d{2}")) {
            View view = View.LISTONEDAY;
            c = new ViewCommand(command, view);
        } else if (commandLength >= 4 && command.substring(0, 4).equals("todo")) {
            Add add = Add.TODO;
            c = new AddCommand(command, add);
        } else if (commandLength >= 8 && command.substring(0, 8).equals("deadline")) {
            Add add = Add.DEADLINE;
            c = new AddCommand(command, add);
        } else if (commandLength >= 5 && command.substring(0, 5).equals("event")) {
            Add add = Add.EVENT;
            c = new AddCommand(command, add);
        } else if (command.equals("bye")) {
            c = new ExitCommand(command);
        } else {
            c = new UnknownCommand(command);
        }
        return c;
    }
}
