package duke.parser;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import duke.commands.Command;
import duke.commands.AddDeadlineCommand;
import duke.commands.AddToDoCommand;
import duke.commands.AddEventCommand;
import duke.commands.ByeCommand;
import duke.commands.DeleteTaskCommand;
import duke.commands.DisplayTaskCommand;
import duke.commands.MarkCommand;
import duke.commands.UnmarkCommand;

import duke.data.task.Task;
import duke.data.task.Event;
import duke.data.task.Deadline;
import duke.data.task.Todo;

import duke.data.exception.DukeException;

public class Parser {

    enum Instruction {bye, list, mark, unmark, todo, deadline, event, delete}

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
            } else {
                throw new DukeException("☹ OOPS!!! The description / by of a deadline cannot be empty.");
            }
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
