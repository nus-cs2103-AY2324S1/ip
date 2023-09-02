package duke.util;

import duke.CheeException;
import duke.command.*;
import duke.task.Deadline;
import duke.task.Event;
import duke.task.Task;
import duke.task.Todo;

import java.time.LocalDate;

public class Parser {

    public static Command parse(String command) {
        if (command.equals("list")) {
            return new ListCommand();
        } else if (command.startsWith("mark")) {
            int index = command.length() - 1;
            char c = command.charAt(index);
            int number = c - 48 - 1;
            return new MarkCommand(number);
        } else if (command.startsWith("unmark")) {
            int index = command.length() - 1;
            char c = command.charAt(index);
            int number = c - 48 - 1;
            return new UnmarkCommand(number);
        } else if (command.startsWith("todo")) {
            try {
                if (command.substring(4).isEmpty()) {
                    throw new CheeException("OOPS!!! The description of a todo cannot be empty.");
                }
                String desc = command.substring(5);
                Task instance = new Todo(desc);
                return new AddCommand(instance);
            } catch (CheeException e) {
                System.out.println(e.getMessage());
            }
        } else if (command.startsWith("deadline")) {
            int index = command.indexOf(47);
            String description = command.substring(9, index - 1);
            String time = command.substring(index + 4);
            LocalDate day = LocalDate.parse(time);
            Task instance = new Deadline(description, day);
            return new AddCommand(instance);
        } else if (command.startsWith("event")) {
            int index1 = command.indexOf(47);
            String description = command.substring(6, index1);
            String duration = command.substring(index1 + 6);
            int index2 = duration.indexOf(47);
            String from = duration.substring(0, index2 - 1);
            String to = duration.substring(index2 + 4);
            LocalDate d1 = LocalDate.parse(from);
            LocalDate d2 = LocalDate.parse(to);
            Task instance = new Event(description, d1, d2);
            return new AddCommand(instance);
        } else if (command.startsWith("delete")) {
            int index = command.length() - 1;
            char c = command.charAt(index);
            int number = c - 48 - 1;
            return new DeleteCommand(number);
        } else if (command.equals("bye")) {
            return new ExitCommand();
        } else if (command.startsWith("find")) {
            String keyword = command.substring(5);
            return new FindCommand(keyword);
        }
        return new InvalidCommand();
    }


}

