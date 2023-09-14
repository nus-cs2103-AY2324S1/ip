package duke;

import duke.command.*;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.Scanner;

/**
 * Interpret input of user and gives out corresponding commands
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

        scanner = new Scanner(input);

        String command = scanner.next();
        listSize = Duke.getListSize();

        switch (command) {
        case "bye":
            return new TerminateCommand();

        case "list":
            return new ListCommand();

        case "mark":
            if (!scanner.hasNextInt()) {
                throw new DukeException("Chewie doesn't see the index of task list.");
            }

            int i = scanner.nextInt();
            return handleMark(i);

        case "unmark":
            if (!scanner.hasNextInt()) {
                throw new DukeException("Chewie doesn't see the index of task list.");
            }

            int k = scanner.nextInt();
            return handleUnmark(k);

        case "delete":
            if (!scanner.hasNextInt()) {
                throw new DukeException("Chewie doesn't see the index of task list.");
            }

            int j = scanner.nextInt();
            return handleDelete(j);

        case "find":
            String findPrompt = scanner.nextLine();
            return handleFind(findPrompt);

        case "deadline":
            String deadlinePrompt = scanner.nextLine();
            return handleDeadline(deadlinePrompt);

        case "todo":
            String ToDoRemain = scanner.nextLine().trim();
            return handleToDo(ToDoRemain);

        case "event":
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

        if (deadlineRemain.length != 2 || deadlineRemain[0].isBlank() || deadlineRemain[1].isBlank()) {
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

        if (eventRemain.length != 2 || eventRemain[0].isBlank()) {
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
        if (index < 1 || index > listSize)
            throw new DukeException("The list doesn't have this index.");

        return new MarkCommand(index - 1);
    }

    private UnmarkCommand handleUnmark (int index) throws DukeException{
        if (index < 1 || index > listSize)
            throw new DukeException("The list doesn't have this index.");

        return new UnmarkCommand(index - 1);
    }

    private DeleteCommand handleDelete (int index) throws DukeException{
        if (index < 1 || index > listSize)
            throw new DukeException("The list doesn't have this index.");

        listSize--;

        return new DeleteCommand(index - 1);
    }

    private FindCommand handleFind (String prompt) throws DukeException{
        prompt = prompt.trim();

        if (prompt.isBlank()) {
            throw new DukeException("There is no keyword");
        }

        return new FindCommand(prompt);
    }

}
