import java.time.LocalDateTime;
public class BlipParser {

    private BlipUI ui = new BlipUI();

    public static int parseToGetIndex (String input) throws EmptyTaskNumberException {
        String[] components = input.split("\\s+", 2);
        // Missing Delete Index.
        if (components.length < 2 || components[1].equals("")) {
            throw new EmptyTaskNumberException("!!! Missing Task Number error !!!");
        }
        int taskNum = Integer.parseInt(components[1]) - 1;
        return taskNum;
    }

    public static String parseToDoInfo (String input) throws EmptyDescriptionException {
        String[] components = input.split("\\s+", 2);
        // Missing Deadline Description.
        if (components.length < 2 || components[1].equals("")) {
            throw new EmptyDescriptionException("!!! Missing TO DO Description !!!\n");
        }
        return components[1];
    }

    public static String[] parseDeadlineInfo (String input) throws EmptyDescriptionException {
            String[] test = input.split("\\s+", 2);
            // Missing Deadline Description.
            if (test.length < 2 || test[1].equals("")) {
                throw new EmptyDescriptionException("!!! Missing DEADLINE Description !!!\n");
            }
            String[] components = test[1].split("\\s*/by\\s*");
            String description = components[0];
            String deadlineDateTime = components[1];
            return new String[] {description, deadlineDateTime};

    }

    public static String[] parseEventInfo (String input) throws EmptyDescriptionException, InvalidCommandException {
        String[] test = input.split("\\s+", 2);
        // Missing Deadline Description.
        if (test.length < 2 || test[1].equals("")) {
            throw new EmptyDescriptionException("!!! Missing DEADLINE Description !!!\n");
        }
        String[] components = test[1].split(" /from | /to ");
        if (components.length < 3) {
            throw new InvalidCommandException("!!! Your command is incomplete !!!");
        }
        String description = components[0];
        String eventStart = components[1];
        String eventEnd = components[2];
        return new String[] {description, eventStart, eventEnd};
    }

    public Command parse(String input) {
        try {
            String[] parts = input.split(" ");

            String command = parts[0].toLowerCase();
            switch (command) {
                case "bye":
                    return new ByeCommand();
                case "list":
                    return new ListCommand();
                case "delete":
                    int indexToDelete = parseToGetIndex(input);
                    return new DeleteCommand(indexToDelete);
                case "todo":
                    String toDoDescription = parseToDoInfo(input);
                    return new ToDoCommand(toDoDescription);
                case "deadline":
                    String[] deadlineInfo = parseDeadlineInfo(input);
                    return new DeadlineCommand(deadlineInfo[0], DateConverter.convertToDateTime(deadlineInfo[1]));
                case "event":
                    String[] eventInfo = parseEventInfo(input);
                    return new EventCommand(eventInfo[0], DateConverter.convertToDateTime(eventInfo[1]),
                            DateConverter.convertToDateTime(eventInfo[2]));
                case "mark":
                    int indexToMark = parseToGetIndex(input);
                    return new MarkCommand(indexToMark);
                case "unmark":
                    int indexToUnmark = parseToGetIndex(input);
                    return new UnmarkCommand(indexToUnmark);
                default:
                    throw new InvalidCommandException("!!!!   Your command is invalid   !!!!");
            }
        } catch (InvalidCommandException e1) {
            System.out.println(e1.getMessage());
            ui.showInvalidCmdErr();
        } catch (EmptyTaskNumberException e2) {
            System.out.println(e2.getMessage());
            ui.showEmptyTaskNumErr();
        } catch (EmptyDescriptionException e3) {
            System.out.println(e3.getMessage());
            ui.showEmptyDescErr();
        } catch (DateTimeFormatException e4) {
            System.out.println(e4.getMessage());
            ui.showDateTimeFormatErr();
        }
    }
}
