package duke;

import duke.command.*;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.Scanner;

public class Parser {

    private Scanner scanner;

    private static DateTimeFormatter format = DateTimeFormatter.ISO_DATE;

    private int listSize;



    public Command readInput(String input) throws DukeException, DateTimeParseException {

        if (input.isEmpty()) {
            throw new DukeException("Chewie don't see any command");
        }

        scanner = new Scanner(input);

        String command = scanner.next();
        listSize = Duke.listSize();

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
            if (i < 1 || i > listSize)
                throw new DukeException("The list doesn't have this index.");

            return new MarkCommand(i - 1);

        case "unmark":
            if (!scanner.hasNextInt()) {
                throw new DukeException("Chewie doesn't see the index of task list.");
            }

            int k = scanner.nextInt();
            if (k < 1 || k > listSize)
                throw new DukeException("The list doesn't have this index.");

            return new UnmarkCommand(k - 1);

        case "delete":
            if (!scanner.hasNextInt()) {
                throw new DukeException("Chewie doesn't see the index of task list.");
            }

            int j = scanner.nextInt();
            if (j < 1 || j > listSize)
                throw new DukeException("The list doesn't have this index.");


            listSize--;

            return new DeleteCommand(j - 1);

        case "deadline":
            String deadlinePrompt = scanner.nextLine();

            if (deadlinePrompt.isBlank())
                throw new DukeException("Chewie says deadline's description cannot be empty.");

            String[] deadlineRemain = deadlinePrompt.split(" /by ");

            if (deadlineRemain.length != 2 || deadlineRemain[0].isBlank() || deadlineRemain[1].isBlank())
                throw new DukeException("Chewie says deadline's description is wrong.");

            String task = deadlineRemain[0].trim();
            String dateString = deadlineRemain[1].trim();

            LocalDate date = LocalDate.parse(dateString,format);

            listSize++;

            return new CreateDeadlineCommand(task,date);

        case "todo":
            String ToDoRemain = scanner.nextLine().trim();
            if (ToDoRemain.isBlank()) {
                throw new DukeException("Chewie says todo's description cannot be empty.");
            }

            listSize++;

            return new CreateToDoCommand(ToDoRemain);

        case "event":
            String eventPrompt = scanner.nextLine().trim();

            if (eventPrompt.isBlank())
                throw new DukeException("Chewie says event's description cannot be empty.");

            String[] eventRemain = eventPrompt.split(" /from ");

            if (eventRemain.length != 2 || eventRemain[0].isBlank())
                throw new DukeException("Chewie says event's description is wrong.");

            String eventTask = eventRemain[0];
            String[] eventDate = eventRemain[1].split(" /to ");

            if (eventDate.length !=2)
                throw new DukeException("Chewie says event's time is wrong.");

            String startDate = eventDate[0];
            String endDate = eventDate[1];

            if (startDate.isBlank() || endDate.isBlank())
                throw new DukeException("Chewie says event's time is wrong.");

            LocalDate start = LocalDate.parse(startDate,format);
            LocalDate end = LocalDate.parse(endDate,format);

            listSize++;

            return new CreateEventCommand(eventTask,start,end);

        default:
            throw new DukeException("Chewie doesn't recgonize this command: " + command);
        }

    }
}
