import java.time.LocalDate;
import java.util.List;
import java.util.Arrays;

/**
 * The Parser class is used to run the infinite loop and
 * take in inputs by the user
 *
 * @author Zi Xiang
 * @version CS2103 AY23/24 Sem 1
 */
public class Parser {
    // Initialisation of objects and variables
    static List<String> commands = Arrays.asList(new String[]{"todo", "deadline", "event", "mark", "unmark", "delete"});;

    public static Command parse(String fullCommand) throws DukeException{
        if (fullCommand.equals("bye")) {
            return new ExitCommand();
        } else if (fullCommand.equals("list")) {
            return new ListCommand();
        } else {
            String[] temp = fullCommand.split(" ", 2);
            String command = temp[0];
            if (commands.contains(command)) {
                if (temp.length == 1 || temp[1].length() == 0) {
                    if (command.equals("mark") || command.equals("unmark") || command.equals("delete")){
                        throw new MissingIndexException();
                    } else {
                        throw new EmptyDescriptionException();
                    }
                } else {
                    if (command.equals("mark")) {
                        int index = Integer.parseInt(temp[1]);
                        return new MarkCommand(index);
                    } else if (command.equals("unmark")) {
                        int index = Integer.parseInt(temp[1]);
                        return new UnmarkCommand(index);
                    } else if (command.equals("todo")) {
                        String todo = temp[1];
                        return new AddCommand(new Todo(todo));
                    } else if (command.equals("deadline")) {
                        String[] items = temp[1].split(" /");
                        if (items.length == 1) {
                            throw new EmptyDescriptionException();
                        } else if (!items[1].startsWith("by ")){
                            throw new UnknownCommandException();
                        } else {
                            if (items[1].length() == 3) {
                                throw new EmptyDescriptionException();
                            } else {
                                LocalDate date = LocalDate.parse(items[1].substring(3));
                                return new AddCommand(new Deadline(items[0], date));
                            }
                        }
                    } else if (command.equals("event")) {
                        String[] items = temp[1].split(" /");
                        if (items.length == 3){
                            // Todo: More Error Catching to be done here
                            if (items[1].startsWith("from ") && items[2].startsWith("to ")) {
                                return new AddCommand(new Event(items[0], items[1].substring(5), items[2].substring(3)));
                            } else {
                                throw new UnknownCommandException();
                            }
                        } else {
                            throw new UnknownCommandException();
                        }
                    } else if (command.equals("delete")){
                        int index = Integer.parseInt(temp[1]);
                        return new DeleteCommand(index);
                    }
                }
            } else {
                return new UnknownCommand();
            }
        }
        return new UnknownCommand();
    }
}
