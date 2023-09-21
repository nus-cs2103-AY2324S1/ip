package duke;

import duke.command.*;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.Scanner;

/**
 * Interprets input of user and gives out corresponding commands
 *
 * @author Lian Zhi Xuan
 */
public class Parser {

    private Scanner scanner;

    private static DateTimeFormatter format = DateTimeFormatter.ISO_DATE;

    private int listSize;


    /**
     *  Interpret input from user to give out commands
     *
     * @param input user input
     * @return corresponding commands to be executed by program
     * @throws DukeException for any error within the program
     * @throws DateTimeParseException for wrong date format input by user
     */
    public Command readInput(String input) throws DukeException, DateTimeParseException {
        if (input.isEmpty()) {
            throw new DukeException("Chewie don't see any command");
        }

        listSize = Duke.getListSize();
        scanner = new Scanner(input);

        String command = scanner.next();

        switch (command) {
        case "bye":
            assert command.equals("bye") : "The command should be bye";
            return new TerminateCommand();

        case "list":
            assert command.equals("list") : "The command should be list";
            return new ListCommand();

        case "mark":
            assert command.equals("mark") : "The command should be mark";
            if (!scanner.hasNextInt()) {
                throw new DukeException("Chewie doesn't see the index of task list.");
            }

            int i = scanner.nextInt();
            return handleMark(i);

        case "unmark":
            assert command.equals("unmark") : "The command should be unmark";
            if (!scanner.hasNextInt()) {
                throw new DukeException("Chewie doesn't see the index of task list.");
            }

            int k = scanner.nextInt();
            return handleUnmark(k);

        case "delete":
            assert command.equals("delete") : "The command should be delete";
            if (!scanner.hasNextInt()) {
                throw new DukeException("Chewie doesn't see the index of task list.");
            }

            int j = scanner.nextInt();
            return handleDelete(j);

        case "find":
            assert command.equals("find") : "The command should be find";
            String findPrompt = scanner.nextLine();
            return handleFind(findPrompt);

        case "assign":
            String assignPrompt = scanner.nextLine();
            return handleAssign(assignPrompt);

        case "remove":
            String removePrompt = scanner.nextLine();
            return handleRemove(removePrompt);

        case "search":
            String searchPrompt = scanner.nextLine();
            return handleSearch(searchPrompt);

        case "deadline":
            assert command.equals("deadline") : "The command should be deadline";
            String deadlinePrompt = scanner.nextLine();
            return handleDeadline(deadlinePrompt);

        case "todo":
            assert command.equals("todo") : "The command should be todo";
            String ToDoRemain = scanner.nextLine().trim();
            return handleToDo(ToDoRemain);

        case "event":
            assert command.equals("event") : "The command should be event";
            String eventPrompt = scanner.nextLine().trim();
            return handleEvent(eventPrompt);

        default:
            throw new DukeException("Chewie doesn't recgonize this command: " + command);
        }

    }

    private CreateToDoCommand handleToDo (String prompt) throws DukeException{
        if (prompt.isBlank()) {
            throw new DukeException("Chewie says todo's description cannot be empty.");
        }

        listSize++;

        return new CreateToDoCommand(prompt);
    }

    private CreateDeadlineCommand handleDeadline (String prompt) throws DukeException, DateTimeParseException{

        if (prompt.isBlank()) {
            throw new DukeException("Chewie says deadline's description cannot be empty.");
        }

        String[] deadlineRemain = prompt.split(" /by ");
        boolean isValidDeadline = deadlineRemain.length != 2 || deadlineRemain[0].isBlank()
                || deadlineRemain[1].isBlank();

        if (isValidDeadline) {
            throw new DukeException("Chewie says deadline's description is wrong.");
        }

        String task = deadlineRemain[0].trim();
        String dateString = deadlineRemain[1].trim();

        LocalDate date = LocalDate.parse(dateString,format);

        listSize++;

        return new CreateDeadlineCommand(task,date);

    }

    private CreateEventCommand handleEvent (String prompt) throws DukeException, DateTimeParseException{

        if (prompt.isBlank()) {
            throw new DukeException("Chewie says event's description cannot be empty.");
        }

        String[] eventRemain = prompt.split(" /from ");

        boolean isValidEvent = eventRemain.length != 2 || eventRemain[0].isBlank();

        if (isValidEvent) {
            throw new DukeException("Chewie says event's description is wrong.");
        }

        String eventTask = eventRemain[0];
        String[] eventDate = eventRemain[1].split(" /to ");

        if (eventDate.length !=2) {
            throw new DukeException("Chewie says event's time is wrong.");
        }

        String startDate = eventDate[0];
        String endDate = eventDate[1];

        if (startDate.isBlank() || endDate.isBlank()) {
            throw new DukeException("Chewie says event's time is wrong.");
        }

        LocalDate start = LocalDate.parse(startDate,format);
        LocalDate end = LocalDate.parse(endDate,format);

        listSize++;

        return new CreateEventCommand(eventTask,start,end);
    }

    private MarkCommand handleMark (int index) throws DukeException{
        if (checkInvalidIndex(index)) {
            throw new DukeException("The list doesn't have this index.");
        }
        return new MarkCommand(index - 1);
    }

    private UnmarkCommand handleUnmark (int index) throws DukeException{
        if (checkInvalidIndex(index)) {
            throw new DukeException("The list doesn't have this index.");
        }
        return new UnmarkCommand(index - 1);
    }

    private DeleteCommand handleDelete (int index) throws DukeException{
        if (checkInvalidIndex(index)) {
            throw new DukeException("The list doesn't have this index.");
        }
        listSize--;

        return new DeleteCommand(index - 1);
    }

    private AssignTagCommand handleAssign(String prompt) throws DukeException {
        String[] tokens = prompt.split(" #");

        if (tokens.length != 2 || tokens[0].isBlank() || tokens[1].isBlank()) {
            throw new DukeException("Chewie can't assign the tag");
        }

        int index = Integer.parseInt(tokens[0].trim());
        String tag = tokens[1].trim();

        if (checkInvalidIndex(index)) {
            throw new DukeException("Chewie can't assign the tag");
        }

        if (Duke.getList().getList().get(index - 1).hasTag(tag)) {
            throw new DukeException("The task already has this tag.");
        }

        return new AssignTagCommand(index - 1,tag);
    }

    private RemoveTagCommand handleRemove(String prompt) throws DukeException {
        String[] tokens = prompt.split(" #");

        if (tokens.length != 2 || tokens[0].isBlank() || tokens[1].isBlank()) {
            throw new DukeException("Chewie can't remove the tag");
        }

        int index = Integer.parseInt(tokens[0].trim());
        String tag = tokens[1].trim();

        if (checkInvalidIndex(index)) {
            throw new DukeException("Chewie can't assign the tag");
        }

        if (!Duke.getList().getList().get(index - 1).hasTag(tag)) {
            throw new DukeException("The task does not have this tag.");
        }

        return new RemoveTagCommand(index - 1,tag);
    }

    private SearchTagCommand handleSearch(String prompt) throws DukeException {
        String[] tokens = prompt.split(" #");

        if (tokens.length != 2 || tokens[1].isBlank()) {
            throw new DukeException("Chewie can't find the tag");
        }

        String tag = tokens[1].trim();

        return new SearchTagCommand(tag);
    }

    private boolean checkInvalidIndex(int i ) {
        return i < 1 || i > listSize;
    }

    private FindCommand handleFind (String prompt) throws DukeException{
        prompt = prompt.trim();

        if (prompt.isBlank()) {
            throw new DukeException("There is no keyword");
        }

        return new FindCommand(prompt);
    }

}
