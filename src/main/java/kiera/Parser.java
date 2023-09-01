package kiera;


import java.time.LocalDate;

import kiera.command.AddCommand;
import kiera.command.Command;
import kiera.command.DeleteCommand;
import kiera.command.ExitCommand;
import kiera.command.FilterCommand;
import kiera.command.ListCommand;
import kiera.command.MarkCommand;
import kiera.exception.KieraException;
import kiera.tasktype.TaskType;


public class Parser {
    public Parser() {
    }

    public static Command parse(String input) throws KieraException {

        if (input.equals("bye")) {
            return new ExitCommand();
        }

        if (input.startsWith("mark")) {
            String desc = input.replace("mark ", "");
            return new MarkCommand(desc, true);
        } else if (input.startsWith("unmark")) {
            String desc = input.replace("unmark ", "");
            return new MarkCommand(desc, false);
        }

        if (input.equals("list")) {
            return new ListCommand();
        }

        if (input.startsWith("delete")) {
            String desc = input.replace("delete ", "");
            return new DeleteCommand(desc);
        }

        if (input.startsWith("deadline-date")) {
            String desc = input.replace("deadline-date ", "");
            return new FilterCommand(TaskType.DEADLINE, desc);
        } else if (input.startsWith("event-date")) {
            String desc = input.replace("event-date ", "");
            return new FilterCommand(TaskType.EVENT, desc);
        }

        if (input.startsWith("deadline-today")) {
            String desc = LocalDate.now().toString();
            return new FilterCommand(TaskType.DEADLINE, desc);
        } else if (input.startsWith("event-today")) {
            String desc = LocalDate.now().toString();
            return new FilterCommand(TaskType.EVENT, desc);
        }

        if (input.startsWith("todo")) {
            String desc = input.replace("todo ", "");
            return new AddCommand(TaskType.TODO, desc);
        } else if (input.startsWith("deadline")) {
            String desc = input.replace("deadline ", "");
            return new AddCommand(TaskType.DEADLINE, desc);
        } else if (input.startsWith("event")) {
            String desc = input.replace("event  ", "");
            return new AddCommand(TaskType.EVENT, desc);
        }

        throw new KieraException("invalid input, i don't understand...");
    }

}
