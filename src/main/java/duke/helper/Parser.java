package duke.helper;
import java.util.Scanner;
import duke.command.*;
import duke.task.DukeException;
public class Parser {
    static Scanner scan;

    public Parser() {
        this.scan = new Scanner(System.in);
    }
    public static Command parse(String command) throws DukeException{
            try {
                Parser.getCommand(command);
                if (command.equals("bye")) {
                    return new ExitCommand();
                }
                else if (command.equals("list")) {
                    return new ListCommand();

                } else if (command.startsWith("mark")) {
                    String tasknum = command.split(" ")[1];
                    return new MarkCommand(Integer.parseInt(tasknum));

                } else if (command.startsWith("unmark")) {
                    String tasknum = command.split(" ")[1];
                    return new UnmarkCommand(Integer.parseInt(tasknum));

                } else if (command.startsWith("delete")) {
                    String tasknum = command.split(" ")[1];
                    return new DeleteCommand(Integer.parseInt(tasknum));
                } else if (command.startsWith("find")) {
                    String keyword = command.split(" ")[1];
                    return new FindCommand(keyword);
                }

                // solve what tasks are to be added here
                else {
                    if (command.startsWith("todo") || command.startsWith("deadline") || command.startsWith("event")) {
                        return new AddCommand(command);
                    }
                    // no proper keyword was given
                }


            } catch (DukeException e) {
                throw e;
            }


        return null;
    }

    static boolean getCommand(String command) throws DukeException{
        String firstword = command.split(" ")[0];
        String[] commands = {"bye","list", "unmark","mark", "todo", "deadline", "event", "delete", "find"};
        for (String c: commands) {
            if(c.equals(firstword)) return true;
        }
        throw new DukeException(" Invalid keyword! ");

    }
}
