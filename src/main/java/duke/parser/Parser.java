package duke.parser;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import duke.commands.AddDeadlineCommand;
import duke.commands.AddEventCommand;
import duke.commands.AddToDoCommand;
import duke.commands.ByeCommand;
import duke.commands.Command;
import duke.commands.DeleteTaskCommand;
import duke.commands.DisplayTaskCommand;
import duke.commands.FindCommand;
import duke.commands.MarkCommand;
import duke.commands.UnmarkCommand;
import duke.data.exception.DukeException;
import duke.data.task.Deadline;
import duke.data.task.Event;
import duke.data.task.Task;
import duke.data.task.Todo;

/**
 * The Parser class takes in the command enter by the user and
 * returns a Command instance based on the command entered
 */
public class Parser {

    enum Instruction { bye, list, mark, unmark, todo, deadline, event, delete, find }

    /**
     * Returns the command object based on the command string entered by the user.
     *
     * @param command the first word entered by the user.
     * @return a new command object based on the input of the user.
     * @throws ParseException when the user enters an invalid date format.
     * @throws DukeException when the user enters a description or date.
     * @throws IllegalArgumentException when the user enters an invalid command.
     */
    public static Command parseCommand(String command) throws ParseException, DukeException, IllegalArgumentException {
        SimpleDateFormat inputDateFormatter = new SimpleDateFormat("yyyy-MM-dd");
        String[] splitCommand = splitCommand(command);
        String firstWord = splitCommand[0];
        String secondWord = splitCommand[1];

        Instruction instruction = Instruction.valueOf(firstWord);
        switch (instruction) {
        case bye:
            return new ByeCommand();
        case list:
            return new DisplayTaskCommand();
        case mark:
            return new MarkCommand(Integer.parseInt(secondWord));
        case unmark:
            return new UnmarkCommand(Integer.parseInt(secondWord));
        case todo:
            if (secondWord.isEmpty()) {
                throw new DukeException("Oops! The description of a todo cannot be empty.");
            }
            return new AddToDoCommand(secondWord);
        case deadline:
            if (secondWord.contains(" /by ")) {
                String deadlineTask = secondWord.split(" /by ")[0];
                Date deadline = inputDateFormatter.parse(secondWord.split(" /by ")[1]);
                return new AddDeadlineCommand(deadlineTask, deadline);
            } else {
                throw new DukeException("☹ OOPS!!! The description / by of a deadline cannot be empty.");
            }
        case event:
            if (secondWord.contains(" /from ") && secondWord.contains(" /to ")) {
                String eventTask = secondWord.split(" /from ")[0];
                Date from = inputDateFormatter.parse(secondWord.split(" /from ")[1].split(" /to ")[0]);
                Date to = inputDateFormatter.parse(secondWord.split(" /from ")[1].split(" /to ")[1]);
                return new AddEventCommand(eventTask, from, to);
            }
            throw new DukeException("☹ OOPS!!! The description / from / to of a event cannot be empty.");
        case delete:
            return new DeleteTaskCommand(Integer.parseInt(secondWord));
        case find:
            return new FindCommand(secondWord);
        default:
            throw new DukeException("☹ OOPS!!! I'm sorry, but I don't know what that means :-(");
        }
    }

    /**
     * Split the user input into two elements, the command and the value
     *
     * @param command The input given by the user
     * @return an array of two strings, the command as the first element and the value as the second
     */
    public static String[] splitCommand(String command) {
        String[] arr = new String[2];
        String secondWord = "";
        if (command.contains(" ")) {
            secondWord = command.split(" ", 2)[1];
        }
        arr[0] = command.split(" ", 2)[0];
        arr[1] = secondWord;
        return arr;
    }

    /**
     * Returns the task object based on each line of the .txt file.
     *
     * @param task the first character of the .txt file
     * @return the task object that has been created using the correct type and whether it is marked or not.
     * @throws ParseException when there is an invalid date format in the .txt file
     * @throws DukeException when the file is corrupted.
     */
    public static Task parseTask(String task) throws ParseException, DukeException {
        SimpleDateFormat readDateFormatter = new SimpleDateFormat("MMM dd yyyy");
        String[] splitTask = task.split(" \\| ");
        assert splitTask[0].equals("T") || splitTask[0].equals("D") || splitTask[0].equals("E") : "Invalid task";
        switch (splitTask[0]) {
        case "T":
            return markTask(new Todo(splitTask[2]), splitTask[1]);
        case "D":
            return markTask(new Deadline(splitTask[2], readDateFormatter.parse(splitTask[3])), splitTask[1]);
        case "E":
            String[] duration = splitTask[3].split("-");
            return markTask(new Event(splitTask[2], readDateFormatter.parse(duration[0]),
                    readDateFormatter.parse(duration[1])), splitTask[1]);
        default:
            throw new DukeException("File is corrupted");
        }
    }

    /**
     * Marks task as done based on input.
     *
     * @param task the task to be marked.
     * @param toMark whether the program should mark the task or not.
     * @return the updated task.
     */
    private static Task markTask(Task task, String toMark) {
        if (toMark.equals("1")) {
            task.markAsDone();
        }
        return task;
    }
}
