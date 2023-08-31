import java.time.LocalDateTime;
import java.util.Arrays;

public class Parser {
    public Command parse(String input) throws DukeException {
        // Ignore empty user input
        if (input.equals("")) return new EmptyCommand();

        // Extract main command first
        String command = input.split(" ")[0];

        // Parse main command
        switch (command) {
        case "bye":
            return new ExitCommand();
        case "list":
            return new ListCommand();
        case "mark":
            return parseMarkCommand(input, "mark");
        case "unmark":
            return parseMarkCommand(input, "unmark");
        case "todo":
            return parseTodoCommand(input);
        case "deadline":
            return parseDeadlineCommand(input);
        case "event":
            return parseEventCommand(input);
        case "delete":
            return parseDeleteCommand(input);
        default:
            throw new DukeException(new String[] {
                "Unrecognized command " + Ui.cTxt(command, Ui.COLOR.PURPLE),
                "Maybe create a new TODO with " + Ui.cTxt("todo", Ui.COLOR.PURPLE) + " read a book?"
            });
        }
    }

    private static String extractTail(String[] item) {
        return String.join(" ",
            Arrays.copyOfRange(
                item, 1, item.length
            )
        );
    }

    private Command parseMarkCommand(String input, String type) throws DukeException{
        String[] parseArr = input.split(" ");
        if (parseArr.length < 2) {
            throw new DukeException(new String[] {
                "Looks like you're missing a number:",
                "Try " + Ui.cTxt("mark", Ui.COLOR.PURPLE) + " 1"
            });
        }

        return new MarkCommand(parseArr[1], type == "mark");
    }

    private Command parseTodoCommand(String input) throws DukeException {
        String[] parseArr = input.split(" ");
        if (parseArr.length < 2) {
            throw new DukeException(new String[] {
                "Looks like you're missing a description:",
                "Try " + Ui.cTxt("todo", Ui.COLOR.PURPLE) + " read a book"
            });
        }

        return new TodoCommand(extractTail(parseArr));
    }

    private Command parseDeadlineCommand(String input) throws DukeException {
        // Split by the "/by" to separate the first and second part. 
        String[] parseArr = input.split("/by "); 

        // Extract the header (command + description).
        String[] header = parseArr[0].split(" ");
            
        // Check if task descripton exists.
        if (header.length < 2) {
            throw new DukeException(new String[] {
                "Looks like you're missing a description:",
                "Try " + Ui.cTxt("deadline", Ui.COLOR.PURPLE) + " submit essay /by Monday, 4pm"
            });
        }
        
        // Check if a date was provided and the "/by" delimiter was supplied.
        if (parseArr.length < 2) {
            throw new DukeException(new String[] {
                "Looks like you're missing a date:",
                "<- Remember to include /by ->",
                "Try " + Ui.cTxt("deadline", Ui.COLOR.PURPLE) + " submit essay /by Monday, 4pm"
            });
        }

        // Extract the date and add a new deadline to the task list.
        LocalDateTime date = DateParser.parseDateString(parseArr[1]);
        if (date == null) {
            throw new DukeException(new String[] {
                "Oops, looks like your date is in an invalid format...",
                "Here are some valid formats:",
                "2023-10-20, 20-10-2023, 2023/10/20, 20/10/2023, Oct 10 2023, 10 Oct 2023",
                "You can provide a timing as well: 2023-10-20 1800"
            });
        }
        return new DeadlineCommand(
            extractTail(header),
            date
        );
    }

    private Command parseEventCommand(String input) throws DukeException {
        // Split by "/from" to separate the first and (second + third) part. 
        String[] parseArr = input.split("/from ");

        // Extract the header (command + description).
        String[] header = parseArr[0].split(" ");

        // Check if task descripton exists.
        if (header.length < 2) {
            throw new DukeException(new String[] {
                "Looks like you're missing a description:",
                "Try " + Ui.cTxt("event", Ui.COLOR.PURPLE) + " NUS carnival /from Aug 21st /to Aug 25th"
            });
        }

        // Check if /from exists.
        if (parseArr.length < 2) {
            throw new DukeException(new String[] {
                "Looks like you're missing " + Ui.cTxt("/from", Ui.COLOR.PURPLE),
                "Try " + Ui.cTxt("event", Ui.COLOR.PURPLE) + " NUS carnival /from Aug 21st /to Aug 25th"
            });
        }

        // Split by "/to" to separate the second and third part. 
        String[] dateParse = parseArr[1].split("/to ");

        // Check if /to exists.
        if (dateParse.length < 2) {
            throw new DukeException(new String[] {
                "Looks like you're missing " + Ui.cTxt("/to", Ui.COLOR.PURPLE),
                "Try " + Ui.cTxt("event", Ui.COLOR.PURPLE) + " NUS carnival /from Aug 21st /to Aug 25th"
            });
        }

        // Extract dates and validate them.
        LocalDateTime fromDate = DateParser.parseDateString(dateParse[0].strip());
        LocalDateTime toDate   = DateParser.parseDateString(dateParse[1].strip());
        if (fromDate == null || toDate == null) {
            throw new DukeException(
                "Oops, looks like your date is in an invalid format..."
            );
        }
        
        return new EventCommand(
            extractTail(header),
            fromDate,
            toDate
        );
    }

    private Command parseDeleteCommand(String input) throws DukeException {
        String[] parseArr = input.split(" ");
        if (parseArr.length < 2) {
            throw new DukeException(new String[] {
                "Looks like you're missing a number:",
                "Try " + Ui.cTxt("delete", Ui.COLOR.PURPLE) + " 1"
            });
        }

        return new DeleteCommand(parseArr[1]);
    }
}
