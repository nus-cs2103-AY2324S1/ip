import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class Parser {

    enum Instruction {bye, list, mark, unmark, todo, deadline, event, delete}

    public static Command parseCommand(String command) throws ParseException, DukeException, IllegalArgumentException {
        SimpleDateFormat inputDateFormatter = new SimpleDateFormat("yyyy-MM-dd");
        String first_word = command.split(" ", 2)[0];
        String second_word = "";

        if (command.contains(" ")) {
            second_word = command.split(" ", 2)[1];
        }

        Instruction instruction = Instruction.valueOf(first_word);
        switch (instruction) {
            case bye:
                return new ByeCommand();
            case list:
                return new DisplayTaskCommand();
            case mark:
                return new MarkCommand(Integer.parseInt(second_word));
            case unmark:
                return new UnmarkCommand(Integer.parseInt(second_word));
            case todo:
                return new AddToDoCommand(second_word);
            case deadline:
                if (second_word.contains(" /by ")) {
                    String deadlineTask = second_word.split(" /by ")[0];
                    Date deadline = inputDateFormatter.parse(second_word.split(" /by ")[1]);
                    return new AddDeadlineCommand(deadlineTask, deadline);
                } else
                    throw new DukeException("☹ OOPS!!! The description / by of a deadline cannot be empty.");
            case event:
                if (second_word.contains(" /from ") && second_word.contains(" /to ")) {
                    String eventTask = second_word.split(" /from ")[0];
                    Date from = inputDateFormatter.parse(second_word.split(" /from ")[1].split(" /to ")[0]);
                    Date to = inputDateFormatter.parse(second_word.split(" /from ")[1].split(" /to ")[1]);
                    return new AddEventCommand(eventTask, from, to);
                }
                throw new DukeException("☹ OOPS!!! The description / from / to of a event cannot be empty.");
            case delete:
                return new DeleteTaskCommand(Integer.parseInt(second_word));
            default:
                throw new DukeException("☹ OOPS!!! I'm sorry, but I don't know what that means :-(");
        }
    }

    public static Task parseTask(String task) throws ParseException, DukeException {
        SimpleDateFormat readDateFormatter = new SimpleDateFormat("MMM dd yyyy");
        String[] splitTask = task.split(" \\| ");
        switch (splitTask[0]) {
            case "T":
                return markTask(new Todo(splitTask[2]), splitTask[1]);
            case "D":
                return markTask(new Deadline(splitTask[2], readDateFormatter.parse(splitTask[3])), splitTask[1]);
            case "E":
                String[] duration = splitTask[3].split("-");
                return markTask(new Event(splitTask[2], readDateFormatter.parse(duration[0]), readDateFormatter.parse(duration[1])), splitTask[1]);
            default:
                throw new DukeException("File is corrupted");
        }
    }

    private static Task markTask(Task task, String toMark) {
        if (toMark.equals("1"))
            task.markAsDone();
        return task;
    }
}
